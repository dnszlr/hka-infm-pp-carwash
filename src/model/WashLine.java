package model;

import basic.RandomGenerator;

public class WashLine {

    private final int lineNumber;
    private final int minProgramDuration;
    private boolean empty;


    public WashLine(int lineNumber, int programDuration) {
        this.lineNumber = lineNumber;
        this.minProgramDuration = programDuration;
        this.empty = true;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public boolean isEmpty() {
        return empty;
    }

    public synchronized void wash(Car car) throws InterruptedException {
        long duration = RandomGenerator.generate(0, 5) + minProgramDuration;
        Thread.sleep(duration * 1000);
        System.out.println("Random clean duration in washLine number " + lineNumber + " is " + duration + " minutes for " + car.toString() + ".");
        // TODO do something
    }

}
