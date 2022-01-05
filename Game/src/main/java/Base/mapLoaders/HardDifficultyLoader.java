package Base.mapLoaders;

import Base.objects.abstracts.AbstractFigur;
import Base.objects.implementation.*;
import Base.objects.util.Coordinate;

import java.util.Random;

public class HardDifficultyLoader extends DifficultyLoader {

    private static final String[] FIGUR_ARRAY = {"N", "N", "N", "N", "GG", "M", "M", "N", "N", "N", "BE"};

    @Override
    public AbstractFigur[][] loading(AbstractFigur[][] data, Player player) {
        int goldCount = 5;
        int botCount = 10;
        int botEaterCount = 0;
        data = initDefaultFigurs(data, player);
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
