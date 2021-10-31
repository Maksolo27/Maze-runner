package Base.Strategy;

import Base.Collection.GameCollection;
import Base.Objects.Abstracts.AbstractFigur;
import Base.Objects.Abstracts.AbstractMovingFigur;
import Base.Objects.Enums.Direction;
import Base.Objects.Enums.ObjectType;
import Base.Objects.util.Coordinate;

import java.util.Random;

public class AgressiveStrategy implements MovingStrategy{
    @Override
    public Direction getDirection(GameCollection collection, AbstractMovingFigur current) {

        Direction[] directions = Direction.values();

        Direction resultDirection;

        for (Direction direction : directions) {
            resultDirection = getDirection(collection, current, direction);

            if (resultDirection != Direction.NONE) {
                return resultDirection;
            }

        }

        int counter = 0;

        do {
            Random random = new Random();
            int rendomIndex = random.nextInt(4);
            resultDirection = directions[rendomIndex];
            System.out.println(resultDirection + " " + current);
            counter++;
        } while (!isGround(collection, current, resultDirection) && counter < 20);


        return resultDirection;
    }

    private Direction getDirection(GameCollection collection, AbstractMovingFigur movableObject, Direction direction) {
        Coordinate coordinate = movableObject.getCoordinateNextObject(direction);
        AbstractFigur object = collection.getFigurByCoordinate(coordinate);

        if (object != null && object.getObjectType() == ObjectType.PLAYER) {
            return direction;
        }

        return Direction.NONE;
    }

    private boolean isGround(GameCollection collection, AbstractMovingFigur movableObject, Direction direction) {
        Coordinate coordinate = movableObject.getCoordinateNextObject(direction);
        AbstractFigur object = collection.getFigurByCoordinate(coordinate);

        if (object != null && object.getObjectType() == ObjectType.EMPTINESS) {
            return true;
        }

        return false;
    }

}
