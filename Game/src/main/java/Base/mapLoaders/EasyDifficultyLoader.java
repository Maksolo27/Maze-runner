package Base.mapLoaders;

import Base.objects.Abstracts.AbstractFigur;
import Base.objects.Implementation.*;
import Base.objects.util.Coordinate;

import java.util.Random;

public class EasyDifficultyLoader implements DifficultyLoader {

    private static final String[] array = {"N", "BE", "N", "N", "GG", "GG", "E", "M", "N", "N", "N"};

    short goldCount = 17;
    short botEaterCount = 3;
    short botCount = 1;

    @Override
    public AbstractFigur[][] loading(AbstractFigur[][] data, Player player) {
        AbstractFigur exit = Exit.getExit();
        Random random = new Random();
        int exitIndexX = random.nextInt(11);
        int exitIndexY = random.nextInt(10);
        data[exitIndexY][exitIndexX] = exit;
        player = new Player();
        player.setCoordinate(PLAYER_COORDINATE);
        data[PLAYER_COORDINATE.getY()][PLAYER_COORDINATE.getX()] = player;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                int elemIndex = random.nextInt(array.length);
                if (data[i][j].getClass() == Emptiness.class) {
                    AbstractFigur arrayValue;
                    if (array[elemIndex].equals("M") && botCount > 0) {
                        arrayValue = new Bot();
                        botCount--;
                    } else if (array[elemIndex].equals("GG") && goldCount > 0) {
                        arrayValue = new Gold();
                        goldCount--;
                    } else if (array[elemIndex].equals("BE") && botEaterCount > 0) {
                        arrayValue = new BotEater();
                        botEaterCount--;
                    }else {
                        arrayValue = data[i][j];
                    }
                    arrayValue.setCoordinate(new Coordinate(j, i));
                    data[i][j] = arrayValue;
                }
            }
        }


        return data;


    }
}
