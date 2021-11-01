package main.java.Base.Objects.Implementation;

import main.java.Base.Objects.Abstracts.AbstractFigur;
import main.java.Base.Objects.Enums.ObjectType;

import javax.swing.*;

public class Gold extends AbstractFigur {
    public Gold(){
        setImage(new ImageIcon(getClass().getResource("/images/gold.png")));
        setObjectType(ObjectType.GOLD);
    }
}
