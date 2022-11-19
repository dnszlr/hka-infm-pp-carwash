package basic;

public class IntTo {

    public static String brand(int value) {
        String brand;
        switch (value) {
            case 1:
                brand = "Porsche";
                break;
            case 2:
                brand = "Mercedes";
                break;
            case 3:
                brand = "BMW";
                break;
            case 4:
                brand = "Toyota";
                break;
            case 5:
                brand = "Audi";
                break;
            default:
                brand = "Something went wrong, this is not a car, it's a bike?!";
        }
        return brand;
    }

    public static String color(int value) {
        String color;
        switch (value) {
            case 1:
                color = "Red";
                break;
            case 2:
                color = "Blue";
                break;
            case 3:
                color = "Green";
                break;
            case 4:
                color = "Black";
                break;
            case 5:
                color = "White";
                break;
            default:
                color = "Something went wrong, this car is somehow transparent?!";
        }
        return color;
    }
}
