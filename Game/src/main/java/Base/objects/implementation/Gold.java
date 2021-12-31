package Base.objects.implementation;

import Base.objects.enums.ObjectType;
import Base.objects.implementation.defaultImpl.Figur;

import javax.swing.*;

public class Gold extends Figur {
    public Gold(){
        setImage(new ImageIcon(getClass().getResource("/images/gold.png")));
        setObjectType(ObjectType.GOLD);
    }
}
