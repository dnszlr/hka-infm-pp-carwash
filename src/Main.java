import basic.RandomGenerator;
import model.CarWash;
import thread.CustomerThread;

public class Main {
    public static void main(String[] args) {
        // init CarWash
        CarWash carWash = new CarWash("A1A Car Wash", 5, 4);
        try {
            // First hour 3-5 Cars, Every 3rd car needs interior cleaning
            printHeader("Afternoon ###");
            threadExec(3, 5, 3, carWash);
            // Second hour 4-7 Cars, Every 4th car needs interior cleaning
            printHeader("Rush Hour ###");
            threadExec(4, 7, 4, carWash);
            // Third hour 3-5 Cars, Every car needs interior cleaning (-1 equals every car)
            printHeader("Evening   ###");
            threadExec(3, 5, -1, carWash);
        } catch(InterruptedException ie) {
            System.out.println("An error occurred during execution: " + ie);
        }
    }

    private static void printHeader(String daytime) {
        System.out.println("##################################");
        System.out.println("### The next hour is " + daytime);
        System.out.println("##################################");
    }

    public static void threadExec(int minThreads, int maxThreads, int interiorInterval, CarWash carWash) throws InterruptedException {
        for(int i = 0; i < 12; i++) {
            int randomAmountOfThreads = RandomGenerator.generate(minThreads, maxThreads);
            for(int j = 0; j < randomAmountOfThreads; j++) {
                new CustomerThread(carWash, j == (interiorInterval - 1) || interiorInterval == -1).start();
            }
            Thread.sleep(5000);

        }
    }
}