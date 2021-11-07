package Base.collection;


import Base.objects.Abstracts.AbstractFigur;
import Base.objects.Abstracts.AbstractMovingFigur;
import Base.objects.Enums.Direction;
import Base.objects.Enums.ObjectType;
import Base.objects.Implementation.Player;
import Base.objects.util.Coordinate;
import Base.observer.CollectionPublisher;
import Base.strategy.MovingStrategy;

import java.util.List;

public interface GameCollection extends CollectionPublisher {
    AbstractFigur[][] getData();

    AbstractFigur getFigurByCoordinate(Coordinate coordinate);

    void setObjectByCoordinate(int y, int x, AbstractFigur object);

    Player getPlayer();

    void moveMovableFigur(ObjectType type, Direction direction);

    void moveMovableFigur(ObjectType type, MovingStrategy strategy);

    List<AbstractMovingFigur> getMovebleData();
}
