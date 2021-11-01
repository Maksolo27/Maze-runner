package main.java.Base.Objects.Abstracts;

import main.java.Base.Objects.Enums.Action;
import main.java.Base.Objects.Enums.Direction;
import main.java.Base.Objects.Enums.ObjectType;
import main.java.Base.Objects.util.Coordinate;

public abstract class AbstractMovingFigur extends AbstractFigur {

    public Action process(AbstractFigur nextObject) {
        if(nextObject == null){
            return Action.NONE;
        }
        if(nextObject.getObjectType() == ObjectType.EMPTINESS){
            return Action.MOVE;
        }
        return Action.NONE;
    }

    public int[] move(Direction direction) {

        int y = getCoordinate().getY();
        int x = getCoordinate().getX();

        switch (direction) {
            case UP:
                y--;
                break;
            case DOWN:
                y++;
                break;

            case LEFT:
                x--;
                break;

            case RIGHT:
                x++;
                break;
        }
        return new int[] {y, x};
    }
    public Coordinate getCoordinateNextObject(Direction direction) {
        int x = getCoordinate().getX();
        int y = getCoordinate().getY();


        switch (direction) {
            case UP:
                y--;
                break;
            case DOWN:
                y++;
                break;
            case LEFT:
                x--;
                break;
            case RIGHT:
                x++;
                break;
        }

        Coordinate coordinate = new Coordinate(0, 0);
        coordinate.setX(x);
        coordinate.setY(y);

        return coordinate;

    }


}
