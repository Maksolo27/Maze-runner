package Base.observer;

public interface CollectionPublisher {

    void notifyAllListeners();

    void addListener(CollectionSubscriber listener);
}
