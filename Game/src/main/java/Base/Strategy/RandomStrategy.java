package main.java.Base.Strategy;

import main.java.Base.Collection.GameCollection;
import main.java.Base.Objects.Abstracts.AbstractMovingFigur;
import main.java.Base.Objects.Enums.Direction;

import java.util.Random;

public class RandomStrategy implements MovingStrategy{

    @Override
    public Direction getDirection(GameCollection collection, AbstractMovingFigur movingFigur) {
        Direction[] directions = Direction.values();
        Random random = new Random();
        int index = random.nextInt(4);
        return directions[index];
    }
}
