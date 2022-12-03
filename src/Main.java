import basic.RandomGenerator;
import model.Car;
import model.carWash.AbstractCarWash;
import model.carWash.CarWashLock;
import model.carWash.CarWashSynchronized;
import thread.CustomerThread;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        // init CarWash
        AbstractCarWash carWashSynchronized = new CarWashSynchronized("A1A Car Wash", 5, 4);
        AbstractCarWash carWashLock = new CarWashLock("A1A Car Wash", 5, 4);
        try {
            printHeader("5pm, Afternoon ");
            // First hour 3-5 Cars, Every 3rd car needs interior cleaning
            // threadExec(1,3, 5, 3, carWashSynchronized);
            runnableExec(1, 3, 5, 3, carWashLock);
            printHeader("6pm, Rush Hour ");
            // Second hour 4-7 Cars, Every 4th car needs interior cleaning
            // threadExec(1,4, 7, 4, carWashSynchronized);
            runnableExec(1,4, 7, 4, carWashLock);
            printHeader("7pm, Evening   ");
            // Third hour 3-5 Cars, Every car needs interior cleaning
            // threadExec(1,3, 5, -1, carWashSynchronized);
            runnableExec(1,3, 5, 1, carWashLock);
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
     * Creates a random amount of threads for one minute. After each iteration the main thread sleeps 5 seconds to simulate an hour.
     * @param duration The duration in which new threads should be created (hours scaled to minute)
     * @param minThreads The minimum amount of threads
     * @param maxThreads The maximum amount of threads
     * @param interiorInterval The interval at which the interior of arriving cars should be cleaned (-1 means every cars interior gets cleaned)
     * @param abstractCarWash The car wash
     * @throws InterruptedException Can throw an InterruptedException because threads are put to sleep to simulate the duration between arriving cars.
     */
    public static void threadExec(int duration, int minThreads, int maxThreads, int interiorInterval, AbstractCarWash abstractCarWash) throws InterruptedException {
        LocalTime endTime = LocalTime.now().plus(duration, ChronoUnit.MINUTES);
        int interiorCounter = 1;
        while (LocalTime.now().isBefore(endTime)) {
            int randomAmountOfThreads = RandomGenerator.generate(minThreads, maxThreads);
            for(int j = 0; j < randomAmountOfThreads; j++) {
                new CustomerThread(abstractCarWash, interiorCounter % interiorInterval == 0).start();
                interiorCounter++;
            }
            Thread.sleep(5000);
        }
    }

    /**
     * Creates a ExecutorService with a fixed Thread pool size that is defined via the maxThreads. For this thread pool a random amount of runnables are generated for one minute.
     * @param duration The duration in which new threads should be created (hours scaled to minute)
     * @param minThreads The minimum amount of threads
     * @param maxThreads The maximum amount of threads
     * @param interiorInterval The interval at which the interior of arriving cars should be cleaned (-1 means every cars interior gets cleaned)
     * @param abstractCarWash The car wash
     * @throws InterruptedException Can throw an InterruptedException because threads are put to sleep to simulate the duration between arriving cars.
     */
    public static void runnableExec(int duration, int minThreads, int maxThreads, int interiorInterval, AbstractCarWash abstractCarWash) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(maxThreads);
        LocalTime endTime = LocalTime.now().plus(duration, ChronoUnit.MINUTES);
        int interiorCounter = 1;
        while (LocalTime.now().isBefore(endTime)) {
            int randomAmountOfThreads = RandomGenerator.generate(minThreads, maxThreads);
            for(int j = 0; j < randomAmountOfThreads; j++) {
                pool.execute(() ->  {
                    try {
                        Car car = new Car();
                        if(interiorCounter % interiorInterval == 0) abstractCarWash.cleanInterior(car);
                        abstractCarWash.washCar(car);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
            Thread.sleep(5000);
        }
        pool.shutdown();
    }
}