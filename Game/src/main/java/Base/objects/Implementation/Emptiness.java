package Base.objects.Implementation;

import Base.objects.Abstracts.AbstractFigur;
import Base.objects.Enums.ObjectType;

public class Emptiness extends AbstractFigur {

    public Emptiness(){
        setObjectType(ObjectType.EMPTINESS);
    }
}
