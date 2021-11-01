package main.java.Base.MapLoaders;

import main.java.Base.Objects.Abstracts.AbstractFigur;
import main.java.Base.Objects.Implementation.Player;

public interface DifficultyLoader {

    AbstractFigur[][] loading(AbstractFigur data[][], Player player);
}
