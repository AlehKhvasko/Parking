package service;

import model.Car;
import model.exceptions.AlreadyExistException;
import model.exceptions.NotFoundException;
import repository.CarRepository;

import java.util.ArrayList;

public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public boolean add(Car car){
        boolean exists = false;
            try {
                if (checkIfExists(car)){
                    throw new AlreadyExistException("Already exist");
                }else{
                    carRepository.add(car);
                    System.out.println(car + " has been added");
                    exists = true;
                }
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return exists;
        }

    public boolean delete(Car car){
        boolean deleted = false;
        if(checkIfExists(car)){
            carRepository.delete(car);
            deleted = true;
            System.out.println(car + " has been deleted");
        }else{
            try {
                throw new NotFoundException("such " + car + "doesn't exist");
            } catch (Exception notFound) {
                System.out.println(notFound);;
            }
        }
        return deleted;
    }

    public ArrayList<Car> read(){
        return carRepository.read();
    }

    private boolean checkIfExists(Car car){
        boolean exists = false;
        ArrayList<Car> carArrayList = carRepository.read();
            for (Car singleCar:carArrayList) {
                if (singleCar.getId().equals(car.getId())){
                   exists = true;
                   break;
                }
            }
        return exists;
    }
}
