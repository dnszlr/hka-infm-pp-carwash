package model.carWash;

import model.counter.AbstractCounter;
import model.counter.SemaphoreCounter;
import model.counter.Type;

public class SemaphoreCarWash extends AbstractCarWash {

    public SemaphoreCarWash(String name, int washLines, int interiorCleaningBoxes) {
        super(name, new SemaphoreCounter(washLines, Type.WASHLINE), new SemaphoreCounter(interiorCleaningBoxes, Type.INTERIORCLEANINGBOX));
    }

    @Override
    protected void enter(AbstractCounter line) throws InterruptedException {
        if(line.isFull()) {
            threadPrint(line.get() + " empty " + line + ", I have to wait!");
        }
        line.decrement();
        threadPrint(line.get() + " empty " + line + " after entry.");
    }

    @Override
    protected void exit(AbstractCounter line) {
        line.increment();
        threadPrint(line.get() + " empty " + line + " after exit.");
    }
}
