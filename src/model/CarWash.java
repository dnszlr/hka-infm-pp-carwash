package model;

import basic.RandomGenerator;

public class CarWash {

    private final String name;
    private int washLines;
    private int interiorCleaningBoxes;

    public CarWash(String name, int washLines, int interiorCleaningBoxes) {
        this.name = name;
        this.washLines = washLines;
        this.interiorCleaningBoxes = interiorCleaningBoxes;
    }

    public void carWashVisit(Car car, boolean interior) throws InterruptedException {
        washCar(car);
        if(interior) cleanInterior(car);
    }

    private void washCar(Car car) throws InterruptedException {
        enterWashLine();
        long duration = RandomGenerator.generate(5, 10);
        Thread.sleep(duration * 1000);
        threadPrint(car.toString() + " wash complete, took " + duration + " minutes.");
        exitWashLine();
    }

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

    private synchronized void exitWashLine() {
        washLines++;
        threadPrint("Empty washLines after exit: " + washLines);
        notify();
    }

    private void cleanInterior(Car car) throws InterruptedException {
        enterInteriorCleaningBoxes();
        long duration = (long) RandomGenerator.generate(1, 3) * 5;
        Thread.sleep(duration * 1000);
        threadPrint(car.toString() + " interior cleaning complete, took " + duration + " minutes.");
        exitInteriorCleaningBoxes();
    }

    private synchronized void enterInteriorCleaningBoxes() {
        while(interiorCleaningBoxes == 0) {
            try {
                threadPrint("No free interiorCleaningBoxes: " + washLines);
                wait();
            } catch (InterruptedException ie) {
                System.out.println("Exception in CarWash:washCar " + ie);
            }
        }
        interiorCleaningBoxes--;
        threadPrint("Empty interiorCleaningBoxes after entry: " + washLines);
    }

    private synchronized void exitInteriorCleaningBoxes() {
        interiorCleaningBoxes++;
        threadPrint("Empty interiorCleaningBoxes after exit: " + washLines);
        notify();
    }

    private void threadPrint(String message) {
        System.out.println("Thread Nr." + Thread.currentThread().getId() + " - " + message);
    }

    @Override
    public String toString() {
        return "CarWash{" +
                "name='" + name + '\'' +
                '}';
    }
}
