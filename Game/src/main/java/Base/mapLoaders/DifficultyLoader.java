package Base.mapLoaders;

import Base.objects.abstracts.AbstractFigur;
import Base.objects.implementation.Player;
import Base.objects.util.Coordinate;

public interface DifficultyLoader {

    Coordinate PLAYER_COORDINATE = new Coordinate(5, 6);

    AbstractFigur[][] loading(AbstractFigur data[][], Player player);
}
