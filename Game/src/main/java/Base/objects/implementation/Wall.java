package Base.objects.implementation;

import Base.objects.enums.ObjectType;
import Base.objects.implementation.defaultImpl.Figur;

import javax.swing.*;

public class Wall extends Figur {
    public Wall(){
        setImage(new ImageIcon(getClass().getResource("/images/wall.png")));
        setObjectType(ObjectType.WALL);
    }
}
