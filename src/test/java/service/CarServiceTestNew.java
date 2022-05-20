package service;

import model.Car;
import model.Make;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import repository.CarRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CarServiceTestNew {

    CarRepository carRepository;
    private CarService carService;

    @BeforeEach
    void setUp(){
       carRepository = Mockito.mock(CarRepository.class);
       carService = new CarService(carRepository);
    }


    @Test
    void assert_true_add() {
        //given
        Car car = new Car(1L, Make.Audi, "Q5");
        //when
        when(carService.add(car)).thenReturn(true);
        //then
        assertTrue(carService.add(car) );
    }

    @Test
    void assert_false_add() {
        //given
        Car car = new Car(1L, Make.Audi, "Q5");
        carService.add(car);
        //when
        when(carService.add(car)).thenReturn(false);
        //then
        assertFalse(carService.add(car));
    }

    @Test
    void assert_true_delete() {
            //given
            Car car = new Car(1L, Make.Audi, "Q5");
            //when
            when(carService.delete(car)).thenReturn(true);
            //then
            assertTrue(carService.delete(car));
    }

    @Test
    void read() {
    }
}