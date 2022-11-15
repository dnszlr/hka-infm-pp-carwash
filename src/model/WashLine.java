package model;

import basic.RandomGenerator;

public class WashLine {

    private int lineNumber;

    public WashLine(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void wash(Car car) {
        int duration = RandomGenerator.generate(5, 10);
        System.out.println("Random clean duration in washLine number " + lineNumber + " is " + duration + " minutes.");
        // TODO define wash program duration random 5,6,7,8,9,10 seconds
        // TODO do something
    }

}
