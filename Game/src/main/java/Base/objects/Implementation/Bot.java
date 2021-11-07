package Base.objects.Implementation;

import Base.objects.Abstracts.AbstractFigur;
import Base.objects.Abstracts.AbstractMovingFigur;
import Base.objects.Enums.Action;
import Base.objects.Enums.ObjectType;

import javax.swing.*;

public class Bot extends AbstractMovingFigur {

    public Bot(){
        setImage(new ImageIcon(getClass().getResource("/images/monster_up.jpg")));
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
        if (nextObject.getObjectType() == ObjectType.GOLD){
            return Action.BOT_GOLD;
        }
        return super.process(nextObject);
    }

}
