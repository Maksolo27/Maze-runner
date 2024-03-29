package Base.strategy;

import Base.collection.GameCollection;
import Base.objects.abstracts.AbstractMovingFigur;
import Base.objects.enums.Direction;

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
