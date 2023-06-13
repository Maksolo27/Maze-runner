package Base.collection;


import Base.objects.abstracts.AbstractFigur;
import Base.objects.abstracts.AbstractMovingFigur;
import Base.objects.enums.Direction;
import Base.objects.enums.ObjectType;
import Base.objects.implementation.Player;
import Base.objects.util.Coordinate;
import Base.observer.CollectionPublisher;
import Base.strategy.MovingStrategy;

import java.util.List;

public interface GameCollection extends CollectionPublisher {
    AbstractFigur[][] getData();

    AbstractFigur getFigurByCoordinate(Coordinate coordinate);

    void setObjectByCoordinate(int y, int x, AbstractFigur object);


    void shoot (ObjectType type, Direction direction);

    Player getPlayer();

    void moveMovableFigur(ObjectType type, Direction direction);

    void moveMovableFigur(ObjectType type, MovingStrategy strategy);

    List<AbstractMovingFigur> getMovebleData();

    void shootAllBullets ();
}
