package Base.objects.implementation.defaultImpl;

import Base.objects.abstracts.AbstractFigur;
import Base.objects.enums.ObjectType;
import Base.objects.util.Coordinate;

import javax.swing.*;

/**
 * This
**/
public class Figur extends AbstractFigur {
    @Override
    public Coordinate getCoordinate() {
        return super.getCoordinate();
    }

    @Override
    public void setCoordinate(Coordinate coordinate) {
        super.setCoordinate(coordinate);
    }

    @Override
    public ImageIcon getImage() {
        return super.getImage();
    }

    @Override
    public void setImage(ImageIcon image) {
        super.setImage(image);
    }

    @Override
    public ObjectType getObjectType() {
        return super.getObjectType();
    }

    @Override
    public void setObjectType(ObjectType objectType) {
        super.setObjectType(objectType);
    }
}
