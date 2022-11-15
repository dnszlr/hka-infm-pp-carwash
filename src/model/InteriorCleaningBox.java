package model;

import basic.RandomGenerator;

public class InteriorCleaningBox {

    private int boxNumber;
    private int programDuration;

    public InteriorCleaningBox(int boxNumber, int programDuration) {
        this.boxNumber = boxNumber;
        this.programDuration = programDuration;
    }

    public int getBoxNumber() {
        return boxNumber;
    }

    public void clean(Car car) {
        int duration = RandomGenerator.generate(1,3) * programDuration;
        System.out.println("Random clean duration in box number " + boxNumber + " is " + duration + " minutes.");
        // TODO define clean duration random 5,10,15 seconds, 1 chip? = 5 seconds
        // TODO do something
    }
}
