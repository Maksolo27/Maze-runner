package Base.threads;

import Base.collection.GameCollection;
import Base.objects.Enums.ObjectType;
import Base.strategy.AgressiveStrategy;

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