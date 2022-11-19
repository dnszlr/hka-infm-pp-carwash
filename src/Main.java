import model.CarWash;
import thread.CustomerThread;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // init CarWash
        CarWash carWash = new CarWash("A1A Car Wash", 5, 4);
        List<CustomerThread> ctl = new ArrayList<>();
        for(int i = 0; i < 6; i++) {
            ctl.add(new CustomerThread(carWash));
        }

        for(CustomerThread ct : ctl) {
            ct.start();
        }
    }
}