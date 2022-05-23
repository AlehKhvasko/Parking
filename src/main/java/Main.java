import model.Car;
import model.Make;
import exceptions.AlreadyExistException;
import repository.CarRepository;
import service.CarService;
import utils.PropertyReader;

import java.sql.DriverManager;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws AlreadyExistException {
        try {
            Properties properties = PropertyReader.getProperties();
            String url = properties.getProperty("db.url");
            String user = properties.getProperty("db.user");
            String password = properties.getProperty("db.password");

            Car car = new Car(3L, Make.Lada, "Sedan");
            Car car2 = new Car(3L, Make.Lada, "Sedan");

            CarService carService = new CarService(
                    new CarRepository(DriverManager.getConnection(url, user, password))
            );
            carService.add(car);
            carService.add(car2);

            System.out.println(carService.readAll());
        } catch (Exception e) {
            System.out.println(e);
        }


    }
}
