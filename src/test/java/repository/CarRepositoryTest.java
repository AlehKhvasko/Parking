package repository;

import exceptions.AlreadyExistException;
import io.zonky.test.db.postgres.embedded.EmbeddedPostgres;
import io.zonky.test.db.postgres.embedded.LiquibasePreparer;
import io.zonky.test.db.postgres.junit.EmbeddedPostgresRules;
import io.zonky.test.db.postgres.junit.PreparedDbRule;
import model.Car;
import model.Make;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.postgresql.util.PSQLException;
import utils.PropertyReader;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import static org.junit.jupiter.api.Assertions.*;

class CarRepositoryTest {

    Properties properties = PropertyReader.getProperties();
    String url = properties.getProperty("db.url");
    String user = properties.getProperty("db.user");
    String password = properties.getProperty("db.password");

    CarRepository carRepository = new CarRepository(DriverManager.getConnection(url, user, password));

    //TODO @Rule Where did it come from?
/*    @Rule
    public PreparedDbRule db =
            EmbeddedPostgresRules.preparedDatabase(
                    LiquibasePreparer.forClasspathLocation("lb/lb.sql"));*/

    Car audi = new Car(1L, Make.Audi, "Q5");
    Car bmw = new Car(2L, Make.BMW, "X5");
    Car lada = new Car(3L, Make.Lada, "2105");

    CarRepositoryTest() throws SQLException {
    }

    //@Test
/*    void add_shouldAdd_whenNoSuchPresent() throws IOException, SQLException {

        DataSource postgresDatabase = EmbeddedPostgres.builder().start().getPostgresDatabase();
        CarRepository carRepository = new CarRepository(postgresDatabase.getConnection());


        carRepository.add(audi);

        List<Car> cars = carRepository.readAll();
        Assertions.assertTrue(cars.contains(audi));
    }*/

    @Test
    void add_shouldAdd_whenNoSuchPresent() throws SQLException {
        //given
        Car audiQ3 = new Car(4L, Make.Audi, "Q3");
        //when
        carRepository.add(audiQ3);
        System.out.println(carRepository.readAll());
        //than
        assertEquals(audiQ3,carRepository.readAll().get(0));
        carRepository.delete(audiQ3);
    }

    @Test
    void add_shouldThrowException_whenSuchPresent() throws SQLException {
        //given
        Car audiQ3 = new Car(4L, Make.Audi, "Q3");
        //when
        System.out.println(carRepository.readAll());
        carRepository.add(audiQ3);
        //than
        assertThrows(SQLException.class, ()-> carRepository.add(audiQ3));
        carRepository.delete(audiQ3);
    }

    @Test
    void delete_shouldDelete_whenSuchPresent() throws SQLException {
        //given
        Car audiQ3 = new Car(4L, Make.Audi, "Q3");
        //when
        carRepository.add(audiQ3);
        System.out.println(carRepository.readAll());
        //than
        carRepository.delete(audiQ3);
        assertTrue(carRepository.readAll().isEmpty());
    }

    @Test
    void delete_shouldDelrtr_whenSuchPresent() throws SQLException {
        //given
        Car audiQ3 = new Car(4L, Make.Audi, "Q3");
        //when
        carRepository.add(audiQ3);
        System.out.println(carRepository.readAll());
        //than
        carRepository.delete(audiQ3);
        assertTrue(carRepository.readAll().isEmpty());
    }

    @Test
    void readAll_shouldReturnArray_whenSuchPresent() throws SQLException {
        //given
        Car audiQ3 = new Car(4L, Make.Audi, "Q3");
        Car audiQ4 = new Car(5L, Make.Audi, "Q4");
        //when
        carRepository.add(audiQ3);
        carRepository.add(audiQ4);
        System.out.println(carRepository.readAll());
        //than
        assertEquals(2, carRepository.readAll().size());
        carRepository.delete(audiQ3);
        carRepository.delete(audiQ4);
    }
}