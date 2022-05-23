package model;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(id, car.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
