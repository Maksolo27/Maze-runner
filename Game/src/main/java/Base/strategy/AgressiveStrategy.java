package Base.strategy;

import Base.collection.GameCollection;
import Base.objects.Abstracts.AbstractMovingFigur;
import Base.objects.Enums.Direction;
import Base.objects.Implementation.*;
import Base.objects.util.Coordinate;

import java.util.List;

public class AgressiveStrategy implements MovingStrategy{

    @Override
    public Direction getDirection(GameCollection collection, AbstractMovingFigur current) {
        Direction resultDirection;
        resultDirection = getNextObjectByDirection(current, collection);
        return resultDirection;
    }

    private Direction getNextObjectByDirection(AbstractMovingFigur current, GameCollection collection) {
        Coordinate botCoordinate = current.getCoordinate();
        Coordinate playerCoordinate = new Coordinate(0,0);
        List<AbstractMovingFigur> data = collection.getMovebleData();
        for (AbstractMovingFigur movingFigur: data) {
            if(movingFigur instanceof Player){
                 playerCoordinate = movingFigur.getCoordinate();
            }
        }
        Direction[] directions = getSortDirection(botCoordinate, playerCoordinate);
        for (Direction direction: directions) {
            switch (direction){
                case DOWN:
                    if(isMovingPossible(collection, current.getCoordinateNextObject(Direction.DOWN))){
                        return Direction.DOWN;
                    } else {
                        break;
                    }
                case UP:
                    if(isMovingPossible(collection, current.getCoordinateNextObject(Direction.UP))){
                        return Direction.UP;
                    } else {
                        break;
                    }
                case RIGHT:
                    if(isMovingPossible(collection, current.getCoordinateNextObject(Direction.RIGHT))){
                        return Direction.RIGHT;
                    } else {
                        break;
                    }
                case LEFT:
                    if(isMovingPossible(collection, current.getCoordinateNextObject(Direction.LEFT))){
                        return Direction.LEFT;
                    } else {
                        break;
                    }
            }
        }
        return new RandomStrategy().getDirection(collection, current);
    }

    private boolean isMovingPossible(GameCollection collection, Coordinate nextObjectCoordinate){
        if(nextObjectCoordinate == null){
            return false;
        }
        return (collection.getFigurByCoordinate(nextObjectCoordinate) != null)
                && (collection.getFigurByCoordinate(nextObjectCoordinate).getClass() == Emptiness.class
                || collection.getFigurByCoordinate(nextObjectCoordinate).getClass() == Gold.class
                || collection.getFigurByCoordinate(nextObjectCoordinate).getClass() == Player.class);
    }

    private Direction[] getSortDirection(Coordinate currentCoordinate, Coordinate playerCoordinate){
        Direction[] resultDirections = new Direction[Direction.values().length - 1];
        if(playerCoordinate.getY() > currentCoordinate.getY()){
            resultDirections[0] =  Direction.DOWN;
            resultDirections[3] = Direction.UP;
        }else {
            resultDirections[3] =  Direction.DOWN;
            resultDirections[0] = Direction.UP;
        }
        if(playerCoordinate.getX() > currentCoordinate.getX()){
            resultDirections[1] = Direction.RIGHT;
            resultDirections[2] =  Direction.LEFT;
        }else {
            resultDirections[2] = Direction.RIGHT;
            resultDirections[1] = Direction.LEFT;
        }
        if(playerCoordinate.getY() == currentCoordinate.getY()){
            resultDirections[1] = Direction.DOWN;
            resultDirections[2] = Direction.UP;
            if(playerCoordinate.getX() > currentCoordinate.getX()){
                resultDirections[0] = Direction.RIGHT;
                resultDirections[3] =  Direction.LEFT;
            }else {
                resultDirections[3] = Direction.RIGHT;
                resultDirections[0] = Direction.LEFT;
            }
        }
        if(playerCoordinate.getX() == currentCoordinate.getX()){
            resultDirections[1] = Direction.RIGHT;
            resultDirections[2] = Direction.LEFT;
            if(playerCoordinate.getY() > currentCoordinate.getY()){
                resultDirections[0] =  Direction.DOWN;
                resultDirections[3] = Direction.UP;
            }else {
                resultDirections[3] =  Direction.DOWN;
                resultDirections[0] = Direction.UP;
            }
        }
        return resultDirections;
    }



}
