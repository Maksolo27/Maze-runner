package Base.Threads;

import Base.Collection.GameCollection;
import Base.Objects.Enums.ObjectType;
import Base.Strategy.AgressiveStrategy;
import Base.Strategy.RandomStrategy;

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
