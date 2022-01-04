package Base.mapLoaders;

import Base.objects.abstracts.AbstractFigur;
import Base.objects.implementation.*;
import Base.objects.util.Coordinate;

import java.util.Random;

public class MediumDifficultyLoader extends DifficultyLoader {

    private static final String[] FIGUR_ARRAY = {"N", "N", "N", "N", "N", "GG", "E", "M", "N", "N", "N"};

    short goldCount = 8;
    short botCount = 5;

    @Override
    public AbstractFigur[][] loading(AbstractFigur data[][], Player player) {
        data = initDefaultFigurs(data, player);
        int goldCount = 8;
        int botCount = 5;
        int botEaterCount = 2;
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
                    } else if (FIGUR_ARRAY[elemIndex].equals("BE") && botEaterCount > 0) {
                        arrayValue = new BotEater();
                        botEaterCount--;
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
