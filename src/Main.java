import basic.RandomGenerator;
import model.CarWash;
import thread.CustomerThread;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Main {
    public static void main(String[] args) {
        // init CarWash
        CarWash carWash = new CarWash("A1A Car Wash", 5, 4);
        try {
            printHeader("5pm, Afternoon ");
            // First hour 3-5 Cars, Every 3rd car needs interior cleaning
            threadExec(1,3, 5, 3, carWash);
            printHeader("6pm, Rush Hour ");
            // Second hour 4-7 Cars, Every 4th car needs interior cleaning
            threadExec(1,4, 7, 4, carWash);
            printHeader("7pm, Evening   ");
            // Third hour 3-5 Cars, Every car needs interior cleaning (-1 equals every car)
            threadExec(1,3, 5, -1, carWash);
        } catch(InterruptedException ie) {
            System.out.println("An error occurred during execution: " + ie);
        }
    }

    private static void printHeader(String time) {
        System.out.println();
        System.out.println("\t\t##################################");
        System.out.println("\t\t###### It is " + time + "######");
        System.out.println("\t\t##################################");
        System.out.println();
    }

    /**
     * Creates a random amount of threads for 12 loop iterations. After each iteration the main thread sleeps 5 seconds to simulate an hour.
     * @param duration The duration in which new threads should be created (hours scaled to minutes)
     * @param minThreads The minimum amount of threads
     * @param maxThreads The maximum amount of threads
     * @param interiorInterval The interval at which the interior of arriving cars should be cleaned
     * @param carWash The car wash
     * @throws InterruptedException Can throw an InterruptedException because threads are put to sleep to simulate the duration between arriving cars.
     */
    public static void threadExec(int duration, int minThreads, int maxThreads, int interiorInterval, CarWash carWash) throws InterruptedException {
        LocalTime endTime = LocalTime.now().plus(duration, ChronoUnit.MINUTES);
        int interiorCounter = 1;
        while (LocalTime.now().isBefore(endTime)) {
            int randomAmountOfThreads = RandomGenerator.generate(minThreads, maxThreads);
            for(int j = 0; j < randomAmountOfThreads; j++) {
                new CustomerThread(carWash, interiorInterval == -1 || interiorCounter % interiorInterval == 0).start();
                interiorCounter++;
            }
            Thread.sleep(5000);
        }
    }
}