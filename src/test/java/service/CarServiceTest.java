package service;

import model.Car;
import model.Make;
import model.exceptions.AlreadyExistException;
import model.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.CarRepository;

import java.lang.reflect.Executable;
import java.sql.SQLException;
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
        when(carRepository.read()).thenReturn(carArrayList);
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
        when(carRepository.read()).thenReturn(carArrayList);
        //then
        assertThrows(AlreadyExistException.class, ()-> carService.add(audi));
    }

    @DisplayName("Delete car with normal input")
    @Test
    void shouldDeleteCar_whenCarExistsInDB() throws NotFoundException {
        //given
        ArrayList<Car> carArrayList = new ArrayList<Car>(List.of(audi, bmw, lada));
        //when
        when(carRepository.read()).thenReturn(carArrayList);
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
        when(carRepository.read()).thenReturn(carArrayList);
        //then
        assertThrows(NotFoundException.class, ()-> carService.delete(lada));
    }


}