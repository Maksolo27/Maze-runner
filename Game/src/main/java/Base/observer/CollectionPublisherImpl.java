package Base.observer;

import Base.collection.GameCollection;

import java.util.ArrayList;
import java.util.List;

public abstract class CollectionPublisherImpl  implements GameCollection {
    private List<CollectionSubscriber> collectionSubscribers = new ArrayList<>();

    @Override
    public void notifyAllListeners() {
        for (CollectionSubscriber collectionSubscriber : collectionSubscribers) {
            collectionSubscriber.notifyFromListener();
        }
    }

    @Override
    public void addListener(CollectionSubscriber listener) {
        collectionSubscribers.add(listener);
    }


}
