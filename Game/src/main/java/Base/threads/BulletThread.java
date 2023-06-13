package Base.threads;

import Base.collection.GameCollection;

public class BulletThread extends Thread{
    private GameCollection collection;

    public BulletThread (GameCollection collection) {
        this.collection = collection;
    }

    @Override
    public void run() {
        for (; ; ) {
            collection.shootAllBullets();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



}
