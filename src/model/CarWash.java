package model;

import basic.RandomGenerator;

import java.util.Arrays;

public class CarWash {

    private String name;
    private WashLine[] washLines;
    private InteriorCleaningBox[] interiorCleaningBoxes;

    public CarWash(String name, WashLine[] washLines, InteriorCleaningBox[] interiorCleaningBoxes) {
        this.name = name;
        this.washLines = washLines;
        this.interiorCleaningBoxes = interiorCleaningBoxes;
    }

    public String getName() {
        return name;
    }

    public WashLine[] getWashLines() {
        return washLines;
    }

    public void setWashLines(WashLine[] washLines) {
        this.washLines = washLines;
    }

    public InteriorCleaningBox[] getInteriorCleaningBoxes() {
        return interiorCleaningBoxes;
    }

    public void setInteriorCleaningBoxes(InteriorCleaningBox[] interiorCleaningBoxes) {
        this.interiorCleaningBoxes = interiorCleaningBoxes;
    }

    public void workday() {
        for(int i = 1; i < 180; i++) {
            Car car = new Car(i);
            washLines[RandomGenerator.generate(0, 4)].wash(car);
            interiorCleaningBoxes[RandomGenerator.generate(0, 3)].clean(car);
        }
    }

    @Override
    public String toString() {
        return "CarWash{" +
                "name='" + name + '\'' +
                '}';
    }
}
