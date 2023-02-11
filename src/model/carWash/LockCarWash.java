package model.carWash;

import model.blocker.AbstractCounter;
import model.blocker.IntegerCounter;
import model.blocker.Type;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LockCarWash extends AbstractCarWash {

    private final ReentrantLock lock;
    private final Condition condition;


    public LockCarWash(String name, int washLines, int interiorCleaningBoxes) {
        super(name, new IntegerCounter(washLines, Type.WASHLINE), new IntegerCounter(interiorCleaningBoxes, Type.INTERIORCLEANINGBOX));
        this.lock = new ReentrantLock();
        this.condition = lock.newCondition();
    }

    /**
     * Enter the carWash with locks and condition.await
     */
    @Override
    protected void enter(AbstractCounter line) throws InterruptedException {
        lock.lock();
        try {
            while(line.isFull()) {
                try {
                    threadPrint(line.get() + " empty " + line + ", I have to wait!");
                    condition.await();
                } catch(InterruptedException ie) {
                    System.out.println("Exception in CarWash:enter " + ie);
                }
            }
            line.decrement();
            threadPrint(line.get() + " empty " + line + " after entry.");
        } finally {
            lock.unlock();
        }
    }

    /**
     * Exit the carWash with locks and condition.signal
     */
    @Override
    protected void exit(AbstractCounter line) {
        lock.lock();
        try {
            line.increment();
            threadPrint(line.get() + " empty " + line + " after exit.");
            condition.signal();
        } finally {
            lock.unlock();
        }
    }
}
