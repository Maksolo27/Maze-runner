package Base.Collection;


import Base.Objects.Abstracts.AbstractFigur;
import Base.Objects.Enums.Direction;
import Base.Objects.Enums.ObjectType;
import Base.Objects.Implementation.Player;
import Base.Objects.util.Coordinate;
import Base.Observer.CollectionPublisher;
import Base.Strategy.MovingStrategy;

public interface GameCollection extends CollectionPublisher {
    AbstractFigur[][] getData();

    AbstractFigur getFigurByCoordinate(Coordinate coordinate);

    void setObjectByCoordinate(int y, int x, AbstractFigur object);

    Player getPlayer();

    void moveMovableFigur(ObjectType type, Direction direction);

    void moveMovableFigur(ObjectType type, MovingStrategy strategy);
}
