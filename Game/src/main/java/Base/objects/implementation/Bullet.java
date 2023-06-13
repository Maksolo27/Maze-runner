package Base.objects.implementation;

import Base.objects.abstracts.AbstractFigur;
import Base.objects.abstracts.AbstractMovingFigur;
import Base.objects.enums.Action;
import Base.objects.enums.Direction;
import Base.objects.enums.ObjectType;
import lombok.Data;

import javax.swing.*;
import java.util.Objects;

@Data
public class Bullet extends AbstractMovingFigur {

    private Direction shootingDirection;

    public Bullet (){
        setImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/noicon.png"))));
        setObjectType(ObjectType.BULLET);
    }

    @Override
    public Action process(AbstractFigur nextObject) {
        ObjectType nextObjectType = nextObject.getObjectType();
        if(nextObject == null){
            return Action.NONE;
        }
        if (nextObjectType == ObjectType.BOT) {
            return Action.EAT_BOT;
        }
        if (nextObjectType == ObjectType.BOT_EATER) {
            return Action.NONE;
        }
        if(nextObjectType == ObjectType.EMPTINESS){
            return Action.SHOOT;
        }
        return Action.NONE;
    }
}
