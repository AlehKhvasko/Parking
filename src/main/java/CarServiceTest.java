import exceptions.AlreadyExistException;
import exceptions.NotFoundException;
import model.Car;
import model.Make;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.CarRepository;
import service.CarService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {
    Car audi;
    Car bmw;
    Car lada;

    @InjectMocks
    CarService carService;
    @Mock
    CarRepository carRepository;

    @BeforeEach
    void setUp(){
        audi = new Car(1L, Make.Audi, "Q5");
        bmw = new Car(2L, Make.BMW, "X5");
        lada = new Car(3L, Make.Lada, "2105");
    }


    @DisplayName("Add car method adds car")
    @Test
    void shouldAddObject_whenCorrectInput() throws AlreadyExistException {
        //given
        ArrayList<Car> carArrayList = new ArrayList<>();
        carArrayList.add(bmw);
        carArrayList.add(audi);
        //when
        when(carRepository.readAll()).thenReturn(carArrayList);
        carService.add(lada);
        //then
        verify(carRepository, times(1)).add(lada);
    }

    @DisplayName("Add car method throws exception")
    @Test
    void shouldThrowException_whenCarAlreadyExists() {
        //given
        ArrayList<Car> carArrayList = new ArrayList<Car>(List.of(audi, bmw, lada));
        //when
        when(carRepository.readAll()).thenReturn(carArrayList);
        //then
        assertThrows(AlreadyExistException.class, ()-> carService.add(audi));
    }

    @DisplayName("Delete car with normal input")
    @Test
    void shouldDeleteCar_whenCarExistsInDB() throws NotFoundException {
        //given
        ArrayList<Car> carArrayList = new ArrayList<Car>(List.of(audi, bmw, lada));
        //when
        when(carRepository.readAll()).thenReturn(carArrayList);
        carService.delete(lada);
        //then
        verify(carRepository, times(1)).delete(lada);
    }

    @DisplayName("Delete car when doesn't exist")
    @Test
    void shouldThrowException_whenCarDoesntExistsInDB(){
        //given
        ArrayList<Car> carArrayList = new ArrayList<Car>(List.of(audi, bmw));
        //when
        when(carRepository.readAll()).thenReturn(carArrayList);
        //then
        assertThrows(NotFoundException.class, ()-> carService.delete(lada));
    }

    @DisplayName("Read method reads DB")
    @Test
    void shouldReadDB_whenObjectPresent(){
        //given
        ArrayList<Car> carArrayList = new ArrayList<Car>(List.of(audi));
        //when
        when(carRepository.readAll()).thenReturn(carArrayList);
        //then
        assertEquals(carArrayList, carService.read());
    }

    @DisplayName("Read method return empty array")
    @Test
    void shouldInvokeReadMethod_whenNoObjectPresent(){
        //given
        ArrayList<Car> carArrayList = new ArrayList<Car>();
        //when
        when(carRepository.readAll()).thenReturn(carArrayList);
        carService.read();
        //then
        verify(carRepository, times(1)).readAll();
        assertTrue(carService.read().isEmpty());
    }
}