package Base.mapLoaders.mapLoaders;

import Base.objects.abstracts.AbstractFigur;

public interface LoaderFactory {

    AbstractFigur[][] getMap(Maps type) throws Exception;
}
