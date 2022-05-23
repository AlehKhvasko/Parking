package service;

import model.Car;
import model.exceptions.AlreadyExistException;
import model.exceptions.NotFoundException;
import repository.CarRepository;

import java.util.ArrayList;
import java.util.Optional;

public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void add(Car car) throws AlreadyExistException {
                if (checkIfExists(car).isPresent()){
                    throw new AlreadyExistException("Already exist");
                }else{
                    carRepository.add(car);
                    System.out.println(car + " has been added");
                }
        }

    public void delete(Car car) throws NotFoundException {
        if(checkIfExists(car).isPresent()){
            carRepository.delete(car);
            System.out.println(car + " has been deleted");
        }else{
                throw new NotFoundException("such " + car + "doesn't exist");
        }
    }

    public ArrayList<Car> read(){
        return carRepository.readAll();
    }

    private Optional<Car> checkIfExists(Car car){
        ArrayList<Car> carArrayList = carRepository.readAll();
            for (Car singleCar:carArrayList) {
                if (singleCar.equals(car)){
                   return Optional.of(singleCar);
                }
            }
            return Optional.empty();
    }
}
