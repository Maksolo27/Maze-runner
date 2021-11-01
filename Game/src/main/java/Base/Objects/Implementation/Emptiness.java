package main.java.Base.Objects.Implementation;

import main.java.Base.Objects.Abstracts.AbstractFigur;
import main.java.Base.Objects.Enums.ObjectType;

public class Emptiness extends AbstractFigur {

    public Emptiness(){
        setObjectType(ObjectType.EMPTINESS);
    }
}
