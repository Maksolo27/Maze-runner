package Base.collection;

import Base.db.PlayerDAO;
import Base.mapLoaders.DifficultyLoader;
import Base.mapLoaders.mapLoaders.MapLoaderFactory;
import Base.mapLoaders.mapLoaders.Maps;
import Base.objects.abstracts.AbstractFigur;
import Base.objects.abstracts.AbstractMovingFigur;
import Base.objects.enums.Action;
import Base.objects.enums.Direction;
import Base.objects.enums.ObjectType;
import Base.objects.implementation.Emptiness;
import Base.objects.implementation.Player;
import Base.objects.util.Coordinate;
import Base.observer.CollectionPublisherImpl;
import Base.strategy.MovingStrategy;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

public class ArrayCollection extends CollectionPublisherImpl implements GameCollection {
    private final PlayerDAO playerDAO = new PlayerDAO();
    private AbstractFigur[][] data;
    private final List<AbstractMovingFigur> movingObjects = new CopyOnWriteArrayList<>();
    private Player player = new Player();

    public ArrayCollection(DifficultyLoader difficultyLoader) throws Exception {
        MapLoaderFactory mapLoaderFactory = new MapLoaderFactory();
        data = mapLoaderFactory.getMap(Maps.DATA);
        data = difficultyLoader.loading(this.data, player);
        initOthers();
    }

    private void initOthers() {
        for (AbstractFigur[] datum : data) {
            for (int j = 0; j < datum.length; j++) {
                if (datum[j].getObjectType() == ObjectType.PLAYER) {
                    player = (Player) datum[j];
                }
                if (datum[j] instanceof AbstractMovingFigur) {
                    movingObjects.add((AbstractMovingFigur) datum[j]);
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
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public void setObjectByCoordinate(int y, int x, AbstractFigur object) {
        try {
            object.setCoordinate(new Coordinate(x, y));
            data[y][x] = object;
        }catch (NullPointerException ignored){
        }
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public void moveMovableFigur(ObjectType type, Direction direction) {
        for (AbstractMovingFigur movingFigur : movingObjects) {
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
        player.setGameStatus(action);
        AbstractFigur swapedFigur = new Emptiness();
        switch (action) {
            case WIN:
            case LOSE:
                movingObjects.remove(player);
              //  playerDAO.save(player);
                if(movingFigur.getObjectType() == ObjectType.PLAYER){
                    data[player.getCoordinate().getY()][player.getCoordinate().getX()] = new Emptiness();
                    movingFigur = null;
                }
                nextObject = new Emptiness();
            case EAT_BOT:
                movingObjects.remove(nextObject);
                if(movingFigur.getObjectType() == ObjectType.BOT_EATER) {
                    data[movingFigur.getCoordinate().getY()][movingFigur.getCoordinate().getX()] = new Emptiness();
                }
                nextObject = new Emptiness();
            case BOT_GOLD:
            case BOT_EATER_PLAYER:
                swapedFigur = nextObject;
            case ADD_GOLD:
            case MOVE:
                setObjectByCoordinate(Objects.requireNonNull(movingFigur).getCoordinate().getY(), movingFigur.getCoordinate().getX(), swapedFigur);
                setObjectByCoordinate(y, x, movingFigur);
        }
    }
}
