package Base.objects.implementation;

import Base.objects.enums.ObjectType;
import Base.objects.implementation.defaultImpl.Figur;

import javax.swing.*;
import java.util.Objects;

public class Wall extends Figur {
    public Wall(){
        setImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/wall.png"))));
        setObjectType(ObjectType.WALL);
    }
}
