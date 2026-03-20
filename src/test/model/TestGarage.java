package model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class TestGarage {
    private Garage garage;
    private Car car1;
    private Car car2;

    @BeforeEach
    void runBefore() {
        garage = new Garage();
        
        // Create dummy parts for testing
        Chassis c = new Chassis("Stock", 1000, 0.3, 200);
        Engine e = new Engine("V6", 200, 300, 6000, 6);
        Transmission t = new Transmission("Auto", 100, 5, 200, 0.9);
        
        car1 = new Car("Honda", c, e, t);
        car2 = new Car("Toyota", c, e, t);
    }

    @Test
    void testConstructor() {
        assertEquals(0, garage.getCarCount());
        assertTrue(garage.getCars().isEmpty());
    }

    @Test
    void testAddCar() {
        garage.addCar(car1);
        assertEquals(1, garage.getCarCount());
        assertEquals(car1, garage.getCar(0));
        
        garage.addCar(car2);
        assertEquals(2, garage.getCarCount());
        assertEquals(car2, garage.getCar(1));
    }

    @Test
    void testRemoveCar() {
        garage.addCar(car1);
        assertEquals(1, garage.getCarCount());
        assertEquals(car1, garage.getCar(0));
        
        garage.removeCar(car2);
        assertEquals(1, garage.getCarCount());
        assertEquals(car1, garage.getCar(0));
    }
    
    @Test
    void testGetCars() {
        garage.addCar(car1);
        garage.addCar(car2);
        
        assertEquals(2, garage.getCars().size());
        assertTrue(garage.getCars().contains(car1));
        assertTrue(garage.getCars().contains(car2));
    }
}
