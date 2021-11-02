package Base.Objects.Implementation;

import Base.Objects.Abstracts.AbstractFigur;
import Base.Objects.Enums.ObjectType;

public class Emptiness extends AbstractFigur {

    public Emptiness(){
        setObjectType(ObjectType.EMPTINESS);
    }
}
