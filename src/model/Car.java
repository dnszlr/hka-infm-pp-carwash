package model;

import basic.IntTo;
import basic.RandomGenerator;

public class Car {

    private String brand;
    private String color;

    public Car() {
        this.brand = IntTo.brand(RandomGenerator.generate(1, 5));
        this.color = IntTo.color(RandomGenerator.generate(1, 5));
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
