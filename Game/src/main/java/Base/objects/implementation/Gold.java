package Base.objects.implementation;

import Base.objects.enums.ObjectType;
import Base.objects.implementation.defaultImpl.Figur;

import javax.swing.*;
import java.util.Objects;

public class Gold extends Figur {
    public Gold(){
        setImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/gold.png"))));
        setObjectType(ObjectType.GOLD);
    }
}
