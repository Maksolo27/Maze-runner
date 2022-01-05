package Base.mapLoaders.mapLoaders;

import Base.objects.abstracts.AbstractFigur;
import Base.objects.implementation.Emptiness;
import Base.objects.implementation.Wall;
import Base.objects.implementation.defaultImpl.Figur;

import java.io.FileReader;
import java.io.IOException;

public class MapLoaderFactory implements LoaderFactory {

    private static final String MAP_PATH = "src/main/java/Base/mapLoaders/mapLoaders/maps/";

    public AbstractFigur[][] getMap(Maps type) throws Exception {
        AbstractFigur[][] map = null;
        String fileConsist;
        switch (type) {
            case SPIRAL:
                fileConsist = readFile(MAP_PATH.concat("spiral.txt"));
                map = parseStringToGameMap(fileConsist);
                break;
            case DATA:
                fileConsist = readFile(MAP_PATH.concat("data.txt"));
                map = parseStringToGameMap(fileConsist);
                break;
            default:
                throw new Exception("Not supported Map");
        }
        return map;
    }

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

    private AbstractFigur[][] parseStringToGameMap(String stringMap) throws Exception {
        String separator = getNewLineSeparator();
        int lengthOfWidth = stringMap.split(separator).length;
        int lengthOfHeight = stringMap.split(separator)[0].split(" ").length;
        AbstractFigur[][] map = new Figur[lengthOfWidth][lengthOfHeight];
        String[] width = stringMap.split(separator);
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

    private String getNewLineSeparator() throws Exception {
        String separator = "";
        String os = System.getProperties().getProperty("os.name");
        System.out.println(os);
        switch (os) {
            case "Linux":
                separator = "\n";
                break;
            case "Windows 10":
                separator = "\r\n";
                break;
            default:
                throw new Exception("Not supported os");
        }
        return separator;
    }
}

