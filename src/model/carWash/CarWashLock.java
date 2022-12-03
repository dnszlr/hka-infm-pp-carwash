package model.carWash;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CarWashLock extends AbstractCarWash {

    private final ReentrantLock lock;
    private final Condition condition;

    public CarWashLock(String name, int washLines, int interiorCleaningBoxes) {
        super(name, washLines, interiorCleaningBoxes);
        this.lock = new ReentrantLock();
        this.condition = lock.newCondition();
    }

    /**
     * Synchronized entering of the washing process of one customer
     */
    @Override
    protected void enter(MutableInteger type) {
        lock.lock();
        try {
            while(type.isFull()) {
                try {
                    threadPrint(type.get() + " empty " + type + ", I have to wait!");
                    condition.await();
                } catch(InterruptedException ie) {
                    System.out.println("Exception in CarWash:enter " + ie);
                }
            }
            type.decrement();
            threadPrint(type.get() + " empty " + type + " after entry.");
        } finally {
            lock.unlock();
        }
    }

    /**
     * Synchronized exit of the washing line of one customer
     */
    @Override
    protected void exit(MutableInteger type) {
        lock.lock();
        try {
            type.increment();
            threadPrint(type.get() + " empty " + type + " after exit.");
            condition.signal();
        } finally {
            lock.unlock();
        }
    }
}
