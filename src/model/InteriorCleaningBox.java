package model;

import basic.RandomGenerator;

public class InteriorCleaningBox {

    private final int boxNumber;
    private final int minProgramDuration;
    private boolean empty;

    public InteriorCleaningBox(int boxNumber, int programDuration) {
        this.boxNumber = boxNumber;
        this.minProgramDuration = programDuration;
        this.empty = true;
    }

    public int getBoxNumber() {
        return boxNumber;
    }

    public boolean isEmpty() {
        return empty;
    }

    public synchronized void clean(Car car) throws InterruptedException {
        long duration = (long) RandomGenerator.generate(1, 3) * minProgramDuration;
        Thread.sleep(duration * 1000);
        System.out.println("Random clean duration in box number " + boxNumber + " is " + duration + " minutes for " + car.toString() + ".");
        // TODO define clean duration random 5,10,15 seconds, 1 chip? = 5 seconds
        // TODO do something
    }
}
