import basic.RandomGenerator;
import model.CarWash;
import thread.CustomerThread;

public class Main {
    public static void main(String[] args) {
        // init CarWash
        CarWash carWash = new CarWash("A1A Car Wash", 5, 4);
        try {
            printHeader("5pm, Afternoon ");
            // First hour 3-5 Cars, Every 3rd car needs interior cleaning
            threadExec(3, 5, 3, carWash);
            printHeader("6pm, Rush Hour ");
            // Second hour 4-7 Cars, Every 4th car needs interior cleaning
            threadExec(4, 7, 4, carWash);
            printHeader("7pm, Evening   ");
            // Third hour 3-5 Cars, Every car needs interior cleaning (-1 equals every car)
            threadExec(3, 5, -1, carWash);
        } catch(InterruptedException ie) {
            System.out.println("An error occurred during execution: " + ie);
        }
    }

    private static void printHeader(String time) {
        System.out.println();
        System.out.println("##################################");
        System.out.println("###### It is " + time + "######");
        System.out.println("##################################");
        System.out.println();
    }

    public static void threadExec(int minThreads, int maxThreads, int interiorInterval, CarWash carWash) throws InterruptedException {
        int interiorCounter = 1;
        for(int i = 0; i < 12; i++) {
            int randomAmountOfThreads = RandomGenerator.generate(minThreads, maxThreads);
            for(int j = 0; j < randomAmountOfThreads; j++) {
                new CustomerThread(carWash, interiorInterval == -1 || interiorCounter % interiorInterval == 0).start();
                interiorCounter++;
            }
            Thread.sleep(5000);
        }
    }
}