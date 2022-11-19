package thread;

import model.CarWash;

public class CustomerThread extends Thread {

    private final CarWash carWash;

    public CustomerThread(CarWash carWash) {
        this.carWash = carWash;
    }

    @Override
    public void run() {
        // TODO
    }
}
