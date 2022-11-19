package model;

import java.util.Arrays;
import java.util.Optional;

public class CarWash {

    private final String name;
    private final WashLine[] washLines;
    private final InteriorCleaningBox[] interiorCleaningBoxes;

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

    public InteriorCleaningBox[] getInteriorCleaningBoxes() {
        return interiorCleaningBoxes;
    }

    public Optional<InteriorCleaningBox> getEmptyInteriorCleaningBox() {
        return Arrays.stream(interiorCleaningBoxes).filter(InteriorCleaningBox::isEmpty).findFirst();
    }

    public Optional<WashLine> getEmptyWashLine() {
        return Arrays.stream(washLines).filter(WashLine::isEmpty).findFirst();
    }

    @Override
    public String toString() {
        return "CarWash{" +
                "name='" + name + '\'' +
                '}';
    }
}
