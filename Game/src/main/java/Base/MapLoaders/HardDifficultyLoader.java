package main.java.Base.MapLoaders;

import main.java.Base.Objects.Abstracts.AbstractFigur;
import main.java.Base.Objects.Implementation.*;
import main.java.Base.Objects.util.Coordinate;

import java.util.Random;

public class HardDifficultyLoader implements DifficultyLoader {

    @Override
    public AbstractFigur[][] loading(AbstractFigur [][] data, Player player){
        final String[] array = {"N","N","N","N","GG","M","M","N","N","N"};
        short goldCount = 5;
        short botCount = 10;
        AbstractFigur exit = Exit.getExit();
        Random random = new Random();
        int exitIndexX = random.nextInt(11);
        int exitIndexY = random.nextInt(10);
        data[exitIndexY][exitIndexX] = exit;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                int elemIndex = random.nextInt(array.length);
                if(data[i][j].getClass() == Emptiness.class) {
                    AbstractFigur arrayValue;
                    if (array[elemIndex].equals("M") && botCount > 0){
                        arrayValue = new Bot();
                        botCount--;
                    }
                    else if(array[elemIndex].equals("GG") && goldCount > 0){
                        arrayValue = new Gold();
                        goldCount--;
                    }
                    else {
                        arrayValue = data[i][j];
                    }
                    arrayValue.setCoordinate(new Coordinate(j, i));
                    data[i][j]=arrayValue;
                }

            }

        }
        int playerX = 5;
        int playerY = 6;
        player = new Player();
        player.setCoordinate(new Coordinate(playerX, playerY));

        data[playerY][playerX] = player;
        return data;
    }
}
