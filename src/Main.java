import basic.RandomGenerator;
import model.Car;
import model.carWash.AbstractCarWash;
import model.carWash.LockCarWash;
import model.carWash.SemaphoreCarWash;
import model.carWash.SynchronizedCarWash;
import thread.CustomerThread;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        // init CarWash
        SynchronizedCarWash synchronizedCarWash = new SynchronizedCarWash("A1A Car Wash", 5, 4);
        LockCarWash lockCarWash = new LockCarWash("A1A Car Wash", 5, 4);
        SemaphoreCarWash semaphoreCarWash = new SemaphoreCarWash("A1A Car Wash", 5, 4);
        AbstractCarWash acw = semaphoreCarWash;
        try {
            printHeader("5pm, Afternoon ");
            // First hour 3-5 Cars, Every 3rd car needs interior cleaning
            // threadExec(1, 3, 5, 3, carWashSynchronized);
            runnableExec(1, 3, 5, 3, acw);
            printHeader("6pm, Rush Hour ");
            // Second hour 4-7 Cars, Every 4th car needs interior cleaning
            // threadExec(1, 4, 7, 4, carWashSynchronized);
            runnableExec(1,4, 7, 4, acw);
            printHeader("7pm, Evening   ");
            // Third hour 3-5 Cars, Every car needs interior cleaning
            // threadExec(1, 3, 5, 1, carWashSynchronized);
            runnableExec(1,3, 5, 1, acw);
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
            System.out.println("Arriving Cars: " + randomAmountOfThreads);
            while(randomAmountOfThreads-- > 0) {
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
        ExecutorService pool = Executors.newCachedThreadPool();
        LocalTime endTime = LocalTime.now().plus(duration, ChronoUnit.MINUTES);
        int interiorCounter = 1;
        while (LocalTime.now().isBefore(endTime)) {
            int randomAmountOfThreads = RandomGenerator.generate(minThreads, maxThreads);
            System.out.println("Arriving Cars: " + randomAmountOfThreads);
            while(randomAmountOfThreads-- > 0) {
                boolean interior = interiorCounter % interiorInterval == 0;
                pool.execute(() ->  {
                    try {
                        Car car = new Car();
                        if(interior) abstractCarWash.cleanInterior(car);
                        abstractCarWash.washCar(car);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
                interiorCounter++;
            }
            Thread.sleep(5000);
        }
        pool.shutdown();
    }
}
