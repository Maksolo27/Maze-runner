package Base.mapLoaders;

import Base.objects.abstracts.AbstractFigur;
import Base.objects.implementation.*;
import Base.objects.util.Coordinate;

import java.util.Random;

public abstract class DifficultyLoader {

    private final Coordinate PLAYER_COORDINATE = new Coordinate(5, 6);
    protected final Random random = new Random();

    public abstract AbstractFigur[][] loading(AbstractFigur[][] data, Player player);

    protected AbstractFigur[][] initDefaultFigurs(AbstractFigur[][] data, Player player) {
        AbstractFigur exit = Exit.getExit();
        int exitIndexX = random.nextInt(11);
        int exitIndexY = random.nextInt(10);
        data[exitIndexY][exitIndexX] = exit;
        player = new Player();
        player.setCoordinate(PLAYER_COORDINATE);
        data[PLAYER_COORDINATE.getY()][PLAYER_COORDINATE.getX()] = player;
        return data;
    }
}
