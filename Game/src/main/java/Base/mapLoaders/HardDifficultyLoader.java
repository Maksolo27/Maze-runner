package Base.mapLoaders;

import Base.objects.abstracts.AbstractFigur;
import Base.objects.implementation.*;
import Base.objects.util.Coordinate;

import java.util.Random;

public class HardDifficultyLoader implements DifficultyLoader {

    private static final String[] FIGUR_ARRAY = {"N", "N", "N", "N", "GG", "M", "M", "N", "N", "N"};

    short goldCount = 5;
    short botCount = 10;

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
                int elemIndex = random.nextInt(FIGUR_ARRAY.length);
                if (data[i][j].getClass() == Emptiness.class) {
                    AbstractFigur arrayValue;
                    if (FIGUR_ARRAY[elemIndex].equals("M") && botCount > 0) {
                        arrayValue = new Bot();
                        botCount--;
                    } else if (FIGUR_ARRAY[elemIndex].equals("GG") && goldCount > 0) {
                        arrayValue = new Gold();
                        goldCount--;
                    } else {
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
