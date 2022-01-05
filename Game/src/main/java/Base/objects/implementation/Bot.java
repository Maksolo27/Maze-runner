package Base.objects.implementation;

import Base.objects.abstracts.AbstractFigur;
import Base.objects.enums.Action;
import Base.objects.enums.ObjectType;
import Base.objects.implementation.defaultImpl.MovingFigur;

import javax.swing.*;
import java.util.Objects;

public class Bot extends MovingFigur {

    public Bot(){
        setImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/monster_up.jpg"))));
        setObjectType(ObjectType.BOT);
    }

    @Override
    public Action process(AbstractFigur nextObject) {
        if (nextObject == null){
            return Action.NONE;
        }
        if(nextObject.getObjectType() == ObjectType.PLAYER){
            return Action.LOSE;
        }
        if(nextObject.getObjectType() == ObjectType.BOT_EATER){
            return Action.EAT_BOT;
        }
        if (nextObject.getObjectType() == ObjectType.GOLD){
            return Action.BOT_GOLD;
        }
        return super.process(nextObject);
    }

}
