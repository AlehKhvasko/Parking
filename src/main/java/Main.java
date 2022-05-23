import model.Car;
import model.Make;
import model.exceptions.AlreadyExistException;
import repository.CarRepository;
import service.CarService;

public class Main {
    public static void main(String[] args) throws AlreadyExistException {
        Car car = new Car(3L, Make.Lada, "Sedan");
        Car car2 = new Car(3L, Make.Lada, "Sedan");

        CarService carService = new CarService(new CarRepository());
        carService.add(car);
        carService.add(car2);

        //carService.delete(car);
        System.out.println(carService.read());
    }
}
