package Base.strategy;

import Base.collection.GameCollection;
import Base.objects.Abstracts.AbstractMovingFigur;
import Base.objects.Enums.Direction;

public interface MovingStrategy {

    Direction getDirection(GameCollection collection, AbstractMovingFigur movingFigur);
}
