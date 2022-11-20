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
            Car car = new Car();
            if(interior) carWash.cleanInterior(car);
            carWash.washCar(car);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
