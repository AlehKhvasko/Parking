package repository;

import model.Car;
import model.Make;

import model.exceptions.AlreadyExistException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import service.CarService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CarRepositoryTest {
    Car audi = new Car(1L, Make.Audi, "Q5");
    Car bmw = new Car(2L, Make.BMW, "X5");
    Car lada = new Car(3L, Make.Lada, "2105");


    @InjectMocks
    CarRepository carRepository = new CarRepository();
    @Mock
    CarService carService = new CarService(carRepository);
    //@InjectMocks private CarRepository carRepository;
    @Mock private Connection mockConnection;
    @Mock private Statement mockStatement;
    @Mock private PreparedStatement mockPreparedStatement;


    @DisplayName("Add car")
    @Test
    void shouldAdd_whenNoSuchPresent() throws AlreadyExistException {
        //given
        //when
        doNothing().when(carService).add(audi);
        carService.add(audi);
        //then
        verify(carService, times(1)).add(audi);
    }

    @DisplayName("Add car throw exception")
    @Test
    void shouldThrowException_whenSuchObjPresentInDB() throws AlreadyExistException {
        //given

        //when
        doThrow(SQLException.class).when(carService).add(audi);
        carService.add(audi);
        //then
        assertThrows(SQLException.class, ()->carService.add(audi));
    }

    @Test
    void delete() {
    }

    @Test
    void read() {
    }
}