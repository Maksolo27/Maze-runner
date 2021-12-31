package Base.mapLoaders;

public class DifficultyLoaderFactory {

    public DifficultyLoader getLoader(DifficultyLoaderType type){
        DifficultyLoader difficultyLoader = null;
        switch (type) {
            case EASYLOADER:
                difficultyLoader = new EasyDifficultyLoader();
                break;
            case MEDIUMLOADER:
                difficultyLoader = new MediumDifficultyLoader();
                break;
            case HARDLOADER:
                difficultyLoader = new HardDifficultyLoader();
                break;
        }
        return difficultyLoader;
        }
    }


