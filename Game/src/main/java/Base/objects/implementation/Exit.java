package Base.objects.implementation;

import Base.objects.enums.ObjectType;
import Base.objects.implementation.defaultImpl.Figur;

import javax.swing.*;
import java.util.Objects;

public class Exit extends Figur {
    private static Exit instance;
    private Exit(){
        setImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/exit.gif"))));
        setObjectType(ObjectType.EXIT);
    };

    public static Exit getExit(){
        if (instance == null){
            instance = new Exit();
        }
        return instance;
    }
}
