package thread;

import model.Car;
import model.carWash.AbstractCarWash;
import model.carWash.CarWashSynchronized;

public class CustomerThread extends Thread {

    private final AbstractCarWash abstractCarWash;
    private final boolean interior;

    public CustomerThread(AbstractCarWash abstractCarWash, boolean interior) {
        this.abstractCarWash = abstractCarWash;
        this.interior = interior;
    }

    @Override
    public void run() {
        try {
            Car car = new Car();
            if(interior) abstractCarWash.cleanInterior(car);
            abstractCarWash.washCar(car);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
