package Base.objects.Implementation;

import Base.objects.Abstracts.AbstractFigur;
import Base.objects.Abstracts.AbstractMovingFigur;
import Base.objects.Enums.Action;
import Base.objects.Enums.ObjectType;

import javax.swing.*;

public class BotEater extends AbstractMovingFigur {

    public BotEater(){
        setImage(new ImageIcon(getClass().getResource("/images/search.png")));
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
            System.out.println("nextObjInBotEat = BOT " );
            return Action.EAT_BOT;
        }
        if (nextObject.getObjectType() == ObjectType.GOLD){
            return Action.BOT_GOLD;
        }
        return super.process(nextObject);
    }
}
