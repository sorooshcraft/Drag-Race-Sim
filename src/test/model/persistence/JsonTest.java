package model.persistence;

import model.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class JsonTest {
    // EFFECTS: verifies that the car has the expected name
    protected void checkCar(String name, Car car) {
        assertEquals(name, car.getName());
    }

    // EFFECTS: verifies the core components of the car match the expected names
    protected void checkCarComponents(String chassis, String engine, String trans, Car car) {
        assertEquals(chassis, car.getChassis().getName());
        assertEquals(engine, car.getEngine().getName());
        assertEquals(trans, car.getTransmission().getName());
    }
}