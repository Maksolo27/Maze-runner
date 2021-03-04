package Base.Objects;

import javax.swing.*;
import java.io.Serializable;

public class Player extends AbstractMovingFigur implements Serializable { ;
    int score = 0;
    transient int countSteps = 50;


    public Player(){
        setImage(new ImageIcon(getClass().getResource("/images/goldman_up.png")));
    }


    public int getScore() {
        return score;
    }

    public int getCountSteps() {
        return countSteps;
    }

    @Override
    protected void moveUp() throws Exception{
        int y = getY();
        int x = getX();
        if(gameMap.data[y-1][x].getClass() == new Gold().getClass()) {
            score += 5;
        }
        gameMap.data[y][x] = new Emptiness();
        y--;
        gameMap.data[y][x] = this;
        setY(y);
        gameMap.drawTable();
        Thread.sleep(600);
    }
    @Override
    protected void moveDown()throws Exception{
        int y = getY();
        int x = getX();
        if(gameMap.data[y+1][x].getClass() == new Gold().getClass()) {
            score += 5;
        }
        gameMap.data[y][x] = new Emptiness();
        y++;
        gameMap.data[y][x] = this;
        setY(y);
        gameMap.drawTable();
        Thread.sleep(600);
    }
    @Override
    protected void moveLeft()throws Exception{
        int y = getY();
        int x = getX();
        if(gameMap.data[y][x-1].getClass() == new Gold().getClass()) {
            score += 5;
        }
        gameMap.data[y][x] = new Emptiness();
        x--;
        gameMap.data[y][x] = this;
        setX(x);
        gameMap.drawTable();
        Thread.sleep(600);
    }
    @Override
    protected void moveRight()throws Exception{
        int y = getY();
        int x = getX();
        if(gameMap.data[y][x+1].getClass() == new Gold().getClass()) {
            score += 5;
        }
        gameMap.data[y][x] = new Emptiness();
        x++;
        gameMap.data[y][x] = this;
        gameMap.drawTable();
        setX(x);
        Thread.sleep(600);
    }
}
