package model;

import basic.RandomGenerator;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CarWash {

    private final String name;
    // Amount of washing lines in the Car Wash park
    private int washLines;
    // Amount of interior cleaning boxes in the Car Wash park
    private int interiorCleaningBoxes;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    public CarWash(String name, int washLines, int interiorCleaningBoxes) {
        this.name = name;
        this.washLines = washLines;
        this.interiorCleaningBoxes = interiorCleaningBoxes;
    }

    /**
     * The washing process of a single car
     * @param car The car of the customer
     * @throws InterruptedException Can throw an InterruptedException because threads are put to sleep to simulate the work performed
     */
    public void washCar(Car car) throws InterruptedException {
        enterWashLine();
        long duration = RandomGenerator.generate(5, 10);
        Thread.sleep(duration * 1000);
        threadPrint(car.toString() + " wash complete, took " + duration + " minutes.");
        exitWashLine();
    }

    /**
     * Synchronized entering of the washing process of one customer
     */
    private synchronized void enterWashLine() {
        while(washLines == 0) {
            try {
                threadPrint("No free washLines: " + washLines);
                wait();
            } catch (InterruptedException ie) {
                System.out.println("Exception in CarWash:washCar " + ie);
            }
        }
        washLines--;
        threadPrint("Empty washLines after entry: " + washLines);
    }

    /**
     * Synchronized exit of the washing line of one customer
     */
    private synchronized void exitWashLine() {
        washLines++;
        threadPrint("Empty washLines after exit: " + washLines);
        notify();
    }

    /**
     * The interior cleaning process of a single car
     * @param car The car of the customer
     * @throws InterruptedException Can throw an InterruptedException because threads are put to sleep to simulate the work performed
     */
    public void cleanInterior(Car car) throws InterruptedException {
        enterInteriorCleaningBoxes();
        long duration = (long) RandomGenerator.generate(1, 3) * 5;
        Thread.sleep(duration * 1000);
        threadPrint(car.toString() + " interior cleaning complete, took " + duration + " minutes.");
        exitInteriorCleaningBoxes();
    }
    /**
     * Synchronized entering of the interior cleaning process of one customer
     */
    private synchronized void enterInteriorCleaningBoxes() {
        while(interiorCleaningBoxes == 0) {
            try {
                threadPrint("No free interiorCleaningBoxes: " + interiorCleaningBoxes);
                wait();
            } catch (InterruptedException ie) {
                System.out.println("Exception in CarWash:washCar " + ie);
            }
        }
        interiorCleaningBoxes--;
        threadPrint("Empty interiorCleaningBoxes after entry: " + interiorCleaningBoxes);
    }

    /**
     * Synchronized exit of the interior cleaning process of one customer
     */
    private synchronized void exitInteriorCleaningBoxes() {
        interiorCleaningBoxes++;
        threadPrint("Empty interiorCleaningBoxes after exit: " + interiorCleaningBoxes);
        notify();
    }

    /**
     * Console print with a given message. Adds the id of the currently running thread to the passed message
     * @param message The message to be printed on the console
     */
    private void threadPrint(String message) {
        System.out.println(dtf.format(LocalTime.now()) + ": Thread Nr." + Thread.currentThread().getId() + " " + message);
    }

    @Override
    public String toString() {
        return "CarWash{" +
                "name='" + name + '\'' +
                '}';
    }
}
