package main.java.Base.Objects.Implementation;

import main.java.Base.Objects.Abstracts.AbstractFigur;
import main.java.Base.Objects.Enums.ObjectType;

import javax.swing.*;

public class Wall extends AbstractFigur {
    public Wall(){
        setImage(new ImageIcon(getClass().getResource("/images/wall.png")));
        setObjectType(ObjectType.WALL);
    }
}
