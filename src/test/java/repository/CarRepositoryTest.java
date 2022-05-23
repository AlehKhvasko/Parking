package repository;

import io.zonky.test.db.postgres.embedded.EmbeddedPostgres;
import io.zonky.test.db.postgres.embedded.LiquibasePreparer;
import io.zonky.test.db.postgres.junit.EmbeddedPostgresRules;
import io.zonky.test.db.postgres.junit.PreparedDbRule;
import io.zonky.test.db.postgres.junit.SingleInstancePostgresRule;
import model.Car;
import model.Make;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

class CarRepositoryTest {

    @Rule
    public PreparedDbRule db =
            EmbeddedPostgresRules.preparedDatabase(
                    LiquibasePreparer.forClasspathLocation("lb/lb.sql"));

    Car audi = new Car(1L, Make.Audi, "Q5");
    Car bmw = new Car(2L, Make.BMW, "X5");
    Car lada = new Car(3L, Make.Lada, "2105");

    @Test
    void add_shouldAdd_whenNoSuchPresent() throws IOException, SQLException {
//        CarRepository carRepository = new CarRepository(db.getTestDatabase().getConnection());

        DataSource postgresDatabase = EmbeddedPostgres.builder().start().getPostgresDatabase();
        CarRepository carRepository = new CarRepository(postgresDatabase.getConnection());


        carRepository.add(audi);

        List<Car> cars = carRepository.readAll();
        Assertions.assertTrue(cars.contains(audi));
    }

}