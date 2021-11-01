package main.java.Base.Collection;

import main.java.Base.MapLoaders.DifficultyLoader;
import main.java.Base.MapLoaders.mapLoaders.MapLoaderFactory;
import main.java.Base.Objects.Abstracts.AbstractFigur;
import main.java.Base.Objects.Abstracts.AbstractMovingFigur;
import main.java.Base.Objects.Enums.Action;
import main.java.Base.Objects.Enums.Direction;
import main.java.Base.Objects.Enums.ObjectType;
import main.java.Base.Objects.Implementation.Emptiness;
import main.java.Base.Objects.Implementation.Player;
import main.java.Base.Objects.Implementation.Wall;
import main.java.Base.Objects.util.Coordinate;
import main.java.Base.Observer.CollectionPublisherImpl;
import main.java.Base.Strategy.MovingStrategy;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ArrayCollection extends CollectionPublisherImpl {
    MapLoaderFactory mapLoaderFactory = new MapLoaderFactory();
    AbstractFigur[][] data = {
            {new Emptiness(), new Emptiness(), new Wall(), new Emptiness(), new Emptiness(), new Wall(), new Emptiness(), new Emptiness(), new Emptiness(), new Emptiness(), new Emptiness()},
            {new Wall(), new Emptiness(), new Wall(), new Emptiness(), new Emptiness(), new Wall(), new Emptiness(), new Emptiness(), new Emptiness(), new Emptiness(), new Wall()},
            {new Wall(), new Emptiness(), new Emptiness(), new Emptiness(), new Emptiness(), new Emptiness(), new Wall(), new Emptiness(), new Emptiness(), new Wall(), new Emptiness()},
            {new Wall(), new Emptiness(), new Emptiness(), new Emptiness(), new Emptiness(), new Emptiness(), new Emptiness(), new Emptiness(), new Emptiness(), new Wall(), new Emptiness()},
            {new Emptiness(), new Wall(), new Wall(), new Emptiness(), new Emptiness(), new Emptiness(), new Wall(), new Emptiness(), new Emptiness(), new Emptiness(), new Emptiness()},
            {new Emptiness(), new Emptiness(), new Wall(), new Emptiness(), new Emptiness(), new Emptiness(), new Emptiness(), new Emptiness(), new Wall(), new Emptiness(), new Emptiness()},
            {new Wall(), new Emptiness(), new Emptiness(), new Emptiness(), new Emptiness(), new Emptiness(), new Emptiness(), new Emptiness(), new Wall(), new Emptiness(), new Emptiness()},
            {new Wall(), new Wall(), new Emptiness(), new Emptiness(), new Emptiness(), new Emptiness(), new Emptiness(), new Emptiness(), new Wall(), new Emptiness(), new Emptiness()},
            {new Emptiness(), new Emptiness(), new Emptiness(), new Emptiness(), new Wall(), new Emptiness(), new Emptiness(), new Emptiness(), new Wall(), new Emptiness(), new Emptiness()},
            {new Emptiness(), new Wall(), new Emptiness(), new Emptiness(), new Wall(), new Emptiness(), new Emptiness(), new Emptiness(), new Wall(), new Emptiness(), new Wall()},
            {new Emptiness(), new Emptiness(), new Emptiness(), new Emptiness(), new Wall(), new Emptiness(), new Emptiness(), new Emptiness(), new Wall(), new Emptiness(), new Emptiness()},
            {new Emptiness(), new Emptiness(), new Emptiness(), new Emptiness(), new Wall(), new Emptiness(), new Emptiness(), new Emptiness(), new Emptiness(), new Emptiness(), new Emptiness()}
    };
    private final List<AbstractMovingFigur> movingObjects = new CopyOnWriteArrayList<>();
    private Player player = new Player();

    public ArrayCollection(DifficultyLoader difficultyLoader) {
        data = difficultyLoader.loading(this.data, player);
        initOthers();
    }

    private void initOthers() {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {

                if (data[i][j].getObjectType() == ObjectType.PLAYER ) {
                    player = (Player) data[i][j];
                }

                if (data[i][j] instanceof AbstractMovingFigur) {
                    movingObjects.add((AbstractMovingFigur) data[i][j]);
                }
            }
        }
    }

    @Override
    public AbstractFigur[][] getData() {
        return data;
    }

    public AbstractFigur getFigurByCoordinate(Coordinate coordinate) {
        try {
            return data[coordinate.getY()][coordinate.getX()];
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void setObjectByCoordinate(int y, int x, AbstractFigur object) {
        try {
            object.setCoordinate(new Coordinate(x, y));
            data[y][x] = object;
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public void moveMovableFigur(ObjectType type, Direction direction) {

        for (int i = 0; i < movingObjects.size(); i++) {
            AbstractMovingFigur movingFigur = movingObjects.get(i);

            if (movingFigur.getObjectType() == type) {
                moveFigurToDirection(movingFigur, direction);
            }

        }

    }

    @Override
    public void moveMovableFigur(ObjectType type, MovingStrategy strategy) {
        for (AbstractMovingFigur movingFigur : movingObjects) {
            if (movingFigur.getObjectType() == type) {
                Direction direction = strategy.getDirection(this, movingFigur);
                moveFigurToDirection(movingFigur, direction);
            }
        }
        notifyAllListeners();
    }

    @Override
    public List<AbstractMovingFigur> getMovebleData() {
        return movingObjects;
    }

    private void moveFigurToDirection(AbstractMovingFigur movingFigur, Direction direction) {
        int[] nextCoord = movingFigur.move(direction);
        int y = nextCoord[0];
        int x = nextCoord[1];
        AbstractFigur nextObject = getFigurByCoordinate(new Coordinate(x, y));
        Action action = movingFigur.process(nextObject);
        AbstractFigur swapedFigur = new Emptiness();
        switch (action) {
            case LOSE:
                movingObjects.remove(player);
                if(movingFigur.getObjectType() == ObjectType.PLAYER){
                    data[player.getCoordinate().getY()][player.getCoordinate().getX()] = new Emptiness();
                    movingFigur = null;
                }
                nextObject = new Emptiness();
            case BOT_GOLD:
                swapedFigur = nextObject;
            case ADD_GOLD:
            case WIN:
            case MOVE:
                setObjectByCoordinate(movingFigur.getCoordinate().getY(), movingFigur.getCoordinate().getX(), swapedFigur);
                setObjectByCoordinate(y, x, movingFigur);
        }
    }
}
