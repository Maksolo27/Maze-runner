package Base.objects.implementation;

import Base.objects.abstracts.AbstractFigur;
import Base.objects.enums.Action;
import Base.objects.enums.ObjectType;
import Base.objects.implementation.defaultImpl.MovingFigur;
import lombok.Data;

//import javax.persistence.*;
import javax.swing.*;
import java.io.Serializable;
import java.util.Objects;

@Data
public class Player extends MovingFigur implements Serializable {

    private int id;
    private int score = 0;
    private int countSteps = 50;
    private String gameStatus = "In game";

    public Player() {
        setImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/goldman_up.png"))));
        setObjectType(ObjectType.PLAYER);
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setCountSteps(int countSteps) {
        this.countSteps = countSteps;
    }

    public void setGameStatus(Action action){
        switch (action){
            case WIN:
                gameStatus = "You win";
                break;
            case LOSE:
                gameStatus = "You lose";
                break;
        }
    }

    public int getScore() {
        return score;
    }

    public int getCountSteps() {
        return countSteps;
    }

    @Override
    public Action process(AbstractFigur nextObject) {
        if(countSteps == 0){
            return Action.LOSE;
        }
        Action result = super.process(nextObject);

        if(result != Action.NONE){
            countSteps--;
            return result;
        }
        if(nextObject == null){
            return Action.NONE;
        }
        if(nextObject.getObjectType() == ObjectType.EXIT){
            return Action.WIN;
        }
        if(nextObject.getObjectType() == ObjectType.GOLD){
            score += 5;
            return Action.ADD_GOLD;
        }
        return Action.NONE;
    }

}
