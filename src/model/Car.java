package model;

import basic.IntTo;
import basic.RandomGenerator;

public class Car {

    private final String brand;
    private final String color;

    public Car() {
        this.brand = IntTo.brand(RandomGenerator.generate(1, 5));
        this.color = IntTo.color(RandomGenerator.generate(1, 5));
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
