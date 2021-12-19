package Base.objects.Implementation;

import Base.objects.Abstracts.AbstractFigur;
import Base.objects.Enums.ObjectType;

import javax.swing.*;

public class Wall extends Emptiness {
    public Wall(){
        setImage(new ImageIcon(getClass().getResource("/images/wall.png")));
        setObjectType(ObjectType.WALL);
    }
}
