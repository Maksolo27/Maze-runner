
package Base.userInterface;

import Base.collection.ArrayCollection;
import Base.collection.GameCollection;
import Base.mapLoaders.*;
import Base.objects.Enums.Direction;
import Base.objects.Enums.ObjectType;
import Base.objects.Implementation.Player;
import Base.observer.CollectionSubscriber;
import Base.threads.GameThread;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

import static java.awt.event.KeyEvent.*;

public class GameMap extends JPanel implements CollectionSubscriber, KeyListener {

    final int WIDTH = 576;
    final int HEIGHT = 576;
    private static final Logger logger = LogManager.getLogger();

    public GameCollection collection;

    void runTheGame() throws Exception {
        logger.info("Run the game");
        GameThread thread = new GameThread(collection);
        thread.start();
    }

    public static void main(String[] args) throws Exception {
        GameMap main = new GameMap();
        main.runTheGame();
    }

    JTable table;
    String[] column = new String[11];
    JLabel labelScore = new JLabel();
    JLabel labelSteps = new JLabel();
    JLabel labelTime = new JLabel();
    JLabel labelGameStatus = new JLabel();

    public GameMap() {
        logger.info("Start initialize Map");
        LoaderFactory loaderFactory = new LoaderFactory();
        DifficultyLoader difficultyLoader = loaderFactory.getLoader(DifficultyLoaderType.EASYLOADER);
        collection = new ArrayCollection(difficultyLoader);
        collection.addListener(this);
        JFrame frame = new JFrame("Maze runner");
        table = new JTable();
        table.setTableHeader(null);
        table.setEnabled(false);
        table.setSize(new Dimension(300, 300));
        table.setRowHeight(26);
        table.setRowSelectionAllowed(false);
        table.setShowHorizontalLines(false);
        table.setShowVerticalLines(false);
        table.setUpdateSelectionOnSort(false);
        table.setVerifyInputWhenFocusTarget(false);
        Arrays.fill(column, "");
        drawTable();
        add(table);
        add(labelScore);
        add(labelSteps);
        add(labelTime);
        add(labelGameStatus);
        frame.setMinimumSize(new Dimension(WIDTH,HEIGHT + 22));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.setVisible(true);
        frame.addKeyListener(this);
        logger.info("End initialize Map");
    }

    private void score(int score) {
        labelScore.setText("Score: " + score);
    }

    private void countSteps(int countSteps) {
        labelSteps.setText("Counter: " + countSteps);
    }

    private void gameStatus(String gameStatus) {
        labelGameStatus.setText("Status: " + gameStatus);
    }

    public void drawTable() {
        table.setModel(new DefaultTableModel(collection.getData(), column));
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(new ImageRenderer());
            TableColumn a = table.getColumnModel().getColumn(i);
            a.setPreferredWidth(26);
        }
    }

    @Override
    public void notifyFromListener() {
        Player player = collection.getPlayer();
        score(player.getScore());
        countSteps(player.getCountSteps());
        gameStatus(player.getGameStatus());
        drawTable();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case VK_UP:
                logger.info("Move up");
                collection.moveMovableFigur(ObjectType.PLAYER,Direction.UP);
                break;
            case VK_LEFT:
                logger.info("Move left");
                collection.moveMovableFigur(ObjectType.PLAYER,Direction.LEFT);
                break;
            case VK_DOWN:
                logger.info("Move down");
                collection.moveMovableFigur(ObjectType.PLAYER,Direction.DOWN);
                break;
            case VK_RIGHT:
                logger.info("Move right");
                collection.moveMovableFigur(ObjectType.PLAYER,Direction.RIGHT);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
