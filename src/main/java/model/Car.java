package model;

public class Car {
    private final Long id;
    private final Make make;
    private final String model;

    public Car(Long id, Make make, String model) {
        this.id = id;
        this.make = make;
        this.model = model;
    }

    @Override
    public String toString() {
        return "Car"  + " id = " + id +
                ", make = " + make +
                ", model = " + model;
    }

    public Long getId() {
        return id;
    }

    public Make getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }
}
