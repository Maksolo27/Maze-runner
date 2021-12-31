package Base.objects.implementation.defaultImpl;

import Base.objects.abstracts.AbstractFigur;
import Base.objects.abstracts.AbstractMovingFigur;
import Base.objects.enums.Action;
import Base.objects.enums.Direction;
import Base.objects.util.Coordinate;

public class MovingFigur extends AbstractMovingFigur {

    @Override
    public Action process(AbstractFigur nextObject) {
        return super.process(nextObject);
    }

    @Override
    public int[] move(Direction direction) {
        return super.move(direction);
    }

    @Override
    public Coordinate getCoordinateNextObject(Direction direction) {
        return super.getCoordinateNextObject(direction);
    }
}
