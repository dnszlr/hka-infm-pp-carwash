package thread;

import model.Car;
import model.CarWash;

public class CustomerThread extends Thread {

    private final CarWash carWash;
    private final boolean interior;

    public CustomerThread(CarWash carWash, boolean interior) {
        this.carWash = carWash;
        this.interior = interior;
    }

    @Override
    public void run() {
        try {
            carWash.carWashVisit(new Car(), interior);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
