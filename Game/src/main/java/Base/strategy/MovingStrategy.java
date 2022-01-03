package Base.strategy;

import Base.collection.GameCollection;
import Base.objects.abstracts.AbstractMovingFigur;
import Base.objects.enums.Direction;

public interface MovingStrategy {

    Direction getDirection(GameCollection collection, AbstractMovingFigur movingFigur);
}
