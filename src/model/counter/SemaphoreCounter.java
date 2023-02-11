package model.counter;

import java.util.concurrent.Semaphore;

public class SemaphoreCounter extends AbstractCounter {

    private final Semaphore semaphore;

    public SemaphoreCounter(int permits, Type type) {
        super(type);
        semaphore = new Semaphore(permits, true);
    }

    @Override
    public int get() {
        return semaphore.availablePermits();
    }

    @Override
    public void increment() {
        semaphore.release();
    }

    @Override
    public void decrement() throws InterruptedException {
        semaphore.acquire();
    }

    @Override
    public boolean isFull() {
        return semaphore.availablePermits() == 0;
    }
}
