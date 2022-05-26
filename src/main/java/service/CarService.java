package service;

import exceptions.AlreadyExistException;
import exceptions.NotFoundException;
import model.Car;
import repository.CarRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void add(Car car) throws SQLException {
        if (getCarFromAll(car).isPresent()) {
            throw new AlreadyExistException("Already exist " + car.getId());
        }
        carRepository.add(car);
        System.out.println(car + " has been added");
    }

    public void delete(Car car) throws SQLException {
        if (getCarFromAll(car).isEmpty()) {
            throw new NotFoundException("such " + car + "doesn't exist");
        }
        carRepository.delete(car);
        System.out.println(car + " has been deleted");
    }

    public List<Car> readAll() throws SQLException {
        return carRepository.readAll();
    }

    private Optional<Car> getCarFromAll(Car car) throws SQLException {
        List<Car> carArrayList = carRepository.readAll();
        for (Car singleCar : carArrayList) {
            if (singleCar.equals(car)) {
                return Optional.of(singleCar);
            }
        }
        return Optional.empty();
    }
}
