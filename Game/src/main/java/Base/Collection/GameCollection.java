package main.java.Base.Collection;


import main.java.Base.Objects.Abstracts.AbstractFigur;
import main.java.Base.Objects.Abstracts.AbstractMovingFigur;
import main.java.Base.Objects.Enums.Direction;
import main.java.Base.Objects.Enums.ObjectType;
import main.java.Base.Objects.Implementation.Player;
import main.java.Base.Objects.util.Coordinate;
import main.java.Base.Observer.CollectionPublisher;
import main.java.Base.Strategy.MovingStrategy;

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
