package model.carWash;

import model.counter.AbstractCounter;
import model.counter.IntegerCounter;
import model.counter.Type;

public class SynchronizedCarWash extends AbstractCarWash {

    public SynchronizedCarWash(String name, int washLines, int interiorCleaningBoxes) {
        super(name, new IntegerCounter(washLines, Type.WASHLINE), new IntegerCounter(interiorCleaningBoxes, Type.INTERIORCLEANINGBOX));
    }

    /**
     * Enter the carWash with synchronized and wait.
     */
    @Override
    protected synchronized void enter(AbstractCounter line) throws InterruptedException {
        while(line.isFull()) {
            try {
                threadPrint(line.get() + " empty " + line + ", I have to wait!");
                wait();
            } catch (InterruptedException ie) {
                System.out.println("Exception in CarWash:enter " + ie);
            }
        }
        line.decrement();
        threadPrint(line.get() + " empty " + line + " after entry.");
    }

    /**
     * Exit the carWash with synchronized and notify.
     */
    @Override
    protected synchronized void exit(AbstractCounter line) {
        line.increment();
        threadPrint(line.get() + " empty " + line + " after exit.");
        notify();
    }
}
