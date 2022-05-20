import model.Car;
import model.Make;
import repository.CarRepository;
import service.CarService;

public class Main {
    public static void main(String[] args){
        Car car = new Car(3L, Make.Lada, "Sedan");

        CarService carService = new CarService(new CarRepository());
        carService.add(car);
        //carService.delete(car);
        System.out.println(carService.read());
    }
}
