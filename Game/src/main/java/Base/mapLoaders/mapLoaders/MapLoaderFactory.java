package Base.mapLoaders.mapLoaders;

import Base.objects.Abstracts.AbstractFigur;
import Base.objects.Implementation.Emptiness;
import Base.objects.Implementation.Wall;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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

    private AbstractFigur[][] parseStringToGameMap(String stringMap) {
        int lengthOfWidth = stringMap.split("\n").length;
        int lengthOfHeight = stringMap.split("\n")[0].split(" ").length;
        AbstractFigur[][] map = new Emptiness[lengthOfWidth][lengthOfHeight];
        String[] width = stringMap.split("\n");
        for (int i = 0; i < width.length ; i++) {
            String [] stringFigurs = width[i].split(" ");
            for (int j = 0; j < stringFigurs.length; j++) {
                String stringFigur = stringFigurs[j];
                if (stringFigur.equals("W")){
                    map[i][j] = new Wall();
                }
                else if (stringFigur.equals("E")){
                    map[i][j] = new Emptiness();
                }
            }
        }
        return map;
    }


    public AbstractFigur[][] getMap(Maps type){
        AbstractFigur[][] map = null;
        String fileConsist;
        switch (type) {
            case SPIRAL:
                fileConsist = readFile("src/main/java/Base/mapLoaders/mapLoaders/spiral.txt");
                map = parseStringToGameMap(fileConsist);
                break;

            case DATA:
                fileConsist = readFile("src/main/java/Base/mapLoaders/mapLoaders/data.txt");
                map = parseStringToGameMap(fileConsist);
                break;
        }
        return map;
    }
}

