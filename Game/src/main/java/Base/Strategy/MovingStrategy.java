package main.java.Base.Strategy;

import main.java.Base.Collection.GameCollection;
import main.java.Base.Objects.Abstracts.AbstractMovingFigur;
import main.java.Base.Objects.Enums.Direction;

public interface MovingStrategy {

    Direction getDirection(GameCollection collection, AbstractMovingFigur movingFigur);
}
