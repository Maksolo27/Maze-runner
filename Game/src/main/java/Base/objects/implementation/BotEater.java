package Base.objects.implementation;

import Base.objects.abstracts.AbstractFigur;
import Base.objects.enums.Action;
import Base.objects.enums.ObjectType;
import Base.objects.implementation.defaultImpl.MovingFigur;

import javax.swing.*;
import java.util.Objects;

public class BotEater extends MovingFigur {

    public BotEater(){
        setImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/minipekka.jpg"))));
        setObjectType(ObjectType.BOT_EATER);
    }

    @Override
    public Action process(AbstractFigur nextObject) {
        if (nextObject == null){
            return Action.NONE;
        }
        if(nextObject.getObjectType() == ObjectType.PLAYER){
            return Action.BOT_EATER_PLAYER;
        }
        if(nextObject.getObjectType() == ObjectType.BOT){
            return Action.EAT_BOT;
        }
        if (nextObject.getObjectType() == ObjectType.GOLD){
            return Action.BOT_GOLD;
        }
        return super.process(nextObject);
    }
}
