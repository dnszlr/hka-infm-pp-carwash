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
            case 3:
                brand = "BMW";
            case 4:
                brand = "Toyota";
            case 5:
                brand = "Audi";
            default:
                brand = "Something went wrong, it's a bike";
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
            case 4:
                color = "Black";
            case 5:
                color = "White";
            default:
                color = "Something went wrong, the car is transparent";
        }
        return color;
    }
}
