package model;

import lombok.Data;

@Data
public class Car {
    private final Long id;
    private final Make make;
    private final String model;
}
