package model.carWash;

public class CarWashSynchronized extends AbstractCarWash {

    public CarWashSynchronized(String name, int washLines, int interiorCleaningBoxes) {
        super(name, washLines, interiorCleaningBoxes);
    }

    /**
     * Enter the carWash with synchronized and wait.
     */
    @Override
    protected synchronized void enter(MutableInteger type) {
        while(type.isFull()) {
            try {
                threadPrint(type.get() + " empty " + type + ", I have to wait!");
                wait();
            } catch (InterruptedException ie) {
                System.out.println("Exception in CarWash:enter " + ie);
            }
        }
        type.decrement();
        threadPrint(type.get() + " empty " + type + " after entry.");
    }

    /**
     * Exit the carWash with synchronized and notify.
     */
    @Override
    protected synchronized void exit(MutableInteger type) {
        type.increment();
        threadPrint(type.get() + " empty " + type + " after exit.");
        notify();
    }
}
