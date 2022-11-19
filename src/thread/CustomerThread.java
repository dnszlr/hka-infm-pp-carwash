package thread;

import model.Car;
import model.CarWash;

public class CustomerThread extends Thread {

    private final CarWash carWash;

    public CustomerThread(CarWash carWash) {
        this.carWash = carWash;
    }

    @Override
    public void run() {
        while(true) {
            Car car = new Car();
            try {
                carWash.carWashVisit(car, false);
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
