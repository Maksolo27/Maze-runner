package main.java.Base.Threads;

import main.java.Base.Collection.GameCollection;
import main.java.Base.Objects.Enums.ObjectType;
import main.java.Base.Strategy.AgressiveStrategy;
import main.java.Base.Strategy.RandomStrategy;

public class GameThread extends Thread{

    private GameCollection collection;

    public GameThread(GameCollection collection) {
        this.collection = collection;
    }

    @Override
    public void run() {
        for (; ; ) {
            collection.moveMovableFigur(ObjectType.BOT, new AgressiveStrategy());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
