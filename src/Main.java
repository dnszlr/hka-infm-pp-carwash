import basic.RandomGenerator;
import model.CarWash;
import model.InteriorCleaningBox;
import model.WashLine;

public class Main {
    public static void main(String[] args) {
        WashLine[] washLines = new WashLine[5];
        InteriorCleaningBox[] interiorCleaningBoxes = new InteriorCleaningBox[4];
        for(int id = 0; id < washLines.length; id++) {
            if(id < washLines.length - 1) {
                interiorCleaningBoxes[id] = new InteriorCleaningBox(id, 5);
            }
            washLines[id] = new WashLine(id);
        }
        CarWash carWash = new CarWash("A1A Car Wash", washLines, interiorCleaningBoxes);

        carWash.workday();
    }
}