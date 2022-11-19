import model.CarWash;
import model.InteriorCleaningBox;
import model.WashLine;

public class Main {
    public static void main(String[] args) {
        // init CarWash
        WashLine[] washLines = new WashLine[5];
        InteriorCleaningBox[] interiorCleaningBoxes = new InteriorCleaningBox[4];
        for(int id = 0; id < interiorCleaningBoxes.length; id++) {
            interiorCleaningBoxes[id] = new InteriorCleaningBox(id, 5);
            washLines[id] = new WashLine(id, 5);
        }
        int id = washLines.length - 1;
        washLines[id] = new WashLine(id, 5);
        CarWash carWash = new CarWash("A1A Car Wash", washLines, interiorCleaningBoxes);
    }
}