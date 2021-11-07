package Base.mapLoaders.mapLoaders;

import Base.objects.Abstracts.AbstractFigur;
import Base.objects.Implementation.Emptiness;
import Base.objects.Implementation.Wall;

import java.io.FileReader;
import java.io.IOException;

public class MapLoaderFactory {

    private String readFile(String path) {
        StringBuilder mapTxt = new StringBuilder();
        try (FileReader reader = new FileReader(path)) {
            int c;
            while ((c = reader.read()) != -1) {
                mapTxt.append((char) c);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return mapTxt.toString();
    }

    private AbstractFigur[][] parseStringToGameMap(String stringMap, int height, int width) {
        return null;
    }


    public AbstractFigur[][] getMap(Maps type){
        AbstractFigur[][] map = null;
        switch (type) {
            case SPIRAL:
                String fileConsist = readFile("C:\\Users\\maxim\\IdeaProjects\\Project\\Game\\src\\main.java.Base\\MapLoaders\\mapLoaders\\spiral.txt");
                map = parseStringToGameMap(fileConsist, 11, 11);
                break;
        }
        return map;
    }
}

