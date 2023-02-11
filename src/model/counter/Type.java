package model.counter;

public enum Type {

    WASHLINE("Wash Line"), INTERIORCLEANINGBOX("Interior Cleaning Box");

    private final String type;
    Type(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }
}
