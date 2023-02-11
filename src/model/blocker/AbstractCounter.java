package model.blocker;

public abstract class AbstractCounter {

    private final Type type;

    public AbstractCounter(Type type) {
        this.type = type;
    }

    public abstract int get();

    public abstract void increment();

    public abstract void decrement() throws InterruptedException;

    public abstract boolean isFull();

    @Override
    public String toString() {
        return type.toString();
    }
}
