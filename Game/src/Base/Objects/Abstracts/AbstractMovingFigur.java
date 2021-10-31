package Base.Objects.Abstracts;

import Base.Objects.Enums.Action;
import Base.Objects.Enums.Direction;
import Base.Objects.Enums.ObjectType;
import Base.Objects.util.Coordinate;

import static Base.Objects.Enums.ObjectType.EMPTINESS;
import static Base.Objects.Enums.ObjectType.GOLD;

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

    public boolean checkToGo(AbstractFigur nextObject) {

        if (nextObject == null) {
            return false;
        }

        switch (nextObject.getObjectType()){
            case EMPTINESS:
            case GOLD:
                return true;
        }

        return false;
    }

    public Action getAction(AbstractFigur nextObject) {

        if (nextObject == null) {
            return Action.NONE;
        }

        if (nextObject.getObjectType() == ObjectType.EMPTINESS) {
            return Action.MOVE;
        }

        return Action.NONE;
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

        Coordinate coordinate = new Coordinate(y, x);

        return coordinate;

    }


}
