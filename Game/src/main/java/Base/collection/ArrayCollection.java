package Base.collection;

import Base.db.PlayerDAO;
import Base.mapLoaders.DifficultyLoader;
import Base.mapLoaders.mapLoaders.MapLoaderFactory;
import Base.objects.Abstracts.AbstractFigur;
import Base.objects.Abstracts.AbstractMovingFigur;
import Base.objects.Enums.Action;
import Base.objects.Enums.Direction;
import Base.objects.Enums.ObjectType;
import Base.objects.Implementation.Emptiness;
import Base.objects.Implementation.Player;
import Base.objects.Implementation.Wall;
import Base.objects.util.Coordinate;
import Base.observer.CollectionPublisherImpl;
import Base.strategy.MovingStrategy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ArrayCollection extends CollectionPublisherImpl implements GameCollection {
    private static final Logger logger = LogManager.getLogger();
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
        } catch (NullPointerException e) {
            logger.warn("Figur not found");
            return null;
        }
    }

    @Override
    public void setObjectByCoordinate(int y, int x, AbstractFigur object) {
        try {
            object.setCoordinate(new Coordinate(x, y));
            data[y][x] = object;
        }catch (NullPointerException e){
            logger.error(e);
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
                PlayerDAO playerDAO = new PlayerDAO();
                playerDAO.save(player);
                movingObjects.remove(player);
                if(movingFigur.getObjectType() == ObjectType.PLAYER){
                    data[player.getCoordinate().getY()][player.getCoordinate().getX()] = new Emptiness();
                    movingFigur = null;
                }
                nextObject = new Emptiness();
            case BOT_GOLD:
                logger.info("Swap bot and gold");
                swapedFigur = nextObject;
            case ADD_GOLD:
            case WIN:
            case MOVE:
                setObjectByCoordinate(movingFigur.getCoordinate().getY(), movingFigur.getCoordinate().getX(), swapedFigur);
                setObjectByCoordinate(y, x, movingFigur);
        }
    }
}
