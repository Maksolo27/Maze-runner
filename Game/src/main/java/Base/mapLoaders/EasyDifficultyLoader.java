package Base.mapLoaders;

import Base.objects.abstracts.AbstractFigur;
import Base.objects.implementation.*;
import Base.objects.util.Coordinate;

public class EasyDifficultyLoader extends DifficultyLoader {

    private static final String[] ARRAY = {"N", "BE", "N", "N", "GG", "GG", "E", "M", "N", "N", "N"};

    @Override
    public AbstractFigur[][] loading(AbstractFigur[][] data, Player player) {
        int goldCount = 17;
        int botEaterCount = 0;
        int botCount = 0;
        data = initDefaultFigurs(data, player);
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                int elemIndex = random.nextInt(ARRAY.length);
                if (data[i][j].getClass() == Emptiness.class) {
                    AbstractFigur arrayValue;
                    if (ARRAY[elemIndex].equals("M") && botCount > 0) {
                        arrayValue = new Bot();
                        botCount--;
                    } else if (ARRAY[elemIndex].equals("GG") && goldCount > 0) {
                        arrayValue = new Gold();
                        goldCount--;
                    } else if (ARRAY[elemIndex].equals("BE") && botEaterCount > 0) {
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
