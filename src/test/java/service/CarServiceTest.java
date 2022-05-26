package service;

import exceptions.AlreadyExistException;
import exceptions.NotFoundException;
import model.Car;
import model.Make;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.CarRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @InjectMocks
    private CarService carService;
    @Mock
    private CarRepository carRepository;

    private Car getLada() {
        return new Car(3L, Make.Lada, "2105");
    }

    private Car getX5() {
        return new Car(2L, Make.BMW, "X5");
    }

    private Car getQ5() {
        return new Car(1L, Make.Audi, "Q5");
    }

    @Test
    void add_shouldAddObject_whenCorrectInput() throws AlreadyExistException, NotFoundException, SQLException {
        //given
        ArrayList<Car> carArrayList = new ArrayList<>();
        carArrayList.add(getX5());
        carArrayList.add(getQ5());
        Car lada = getLada();

        //when
        when(carRepository.readAll()).thenReturn(carArrayList);
        carService.add(lada);

        //then
        verify(carRepository, times(1)).add(lada);
    }

    @Test
    void add_shouldThrowException_whenCarAlreadyExists() throws SQLException {
        //given
        List<Car> carArrayList = List.of(getQ5(), getX5(), getLada());
        //when
        when(carRepository.readAll()).thenReturn(carArrayList);
        //then
        assertThrows(AlreadyExistException.class, () -> carService.add(getQ5()));
    }

    @Test
    void delete_shouldDeleteCar_whenCarExistsInDB() throws NotFoundException, SQLException {
        //given
        ArrayList<Car> carArrayList = new ArrayList<>(List.of(getQ5(), getX5(), getLada()));
        //when
        when(carRepository.readAll()).thenReturn(carArrayList);
        carService.delete(getLada());
        //then
        verify(carRepository, times(1)).delete(getLada());
    }

    @Test
    void delete_shouldThrowException_whenCarDoesntExistsInDB() throws SQLException {
        //given
        ArrayList<Car> carArrayList = new ArrayList<Car>(List.of(getQ5(), getX5()));
        //when
        when(carRepository.readAll()).thenReturn(carArrayList);
        //then
        assertThrows(NotFoundException.class, () -> carService.delete(getLada()));
    }

    @Test
    void readAll_shouldReadDB_whenObjectPresent() throws SQLException {
        //given
        ArrayList<Car> carArrayList = new ArrayList<>(List.of(getQ5()));
        //when
        when(carRepository.readAll()).thenReturn(carArrayList);
        //then
        List<Car> read = carService.readAll();
        assertEquals(carArrayList, read);
    }

    @Test
    void shouldInvokeReadMethod_whenNoObjectPresent() throws SQLException {
        //given
        ArrayList<Car> carArrayList = new ArrayList<>();
        //when
        when(carRepository.readAll()).thenReturn(carArrayList);
        carService.readAll();
        //then
        verify(carRepository, times(1)).readAll();
        assertTrue(carService.readAll().isEmpty());
    }
}