package Base.mapLoaders;

import Base.objects.Abstracts.AbstractFigur;
import Base.objects.Implementation.Player;
import Base.objects.util.Coordinate;

public interface DifficultyLoader {

    Coordinate PLAYER_COORDINATE = new Coordinate(5, 6);

    AbstractFigur[][] loading(AbstractFigur data[][], Player player);
}
