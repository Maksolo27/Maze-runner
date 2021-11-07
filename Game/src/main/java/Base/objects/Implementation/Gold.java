package Base.objects.Implementation;

import Base.objects.Abstracts.AbstractFigur;
import Base.objects.Enums.ObjectType;

import javax.swing.*;

public class Gold extends AbstractFigur {
    public Gold(){
        setImage(new ImageIcon(getClass().getResource("/images/gold.png")));
        setObjectType(ObjectType.GOLD);
    }
}
