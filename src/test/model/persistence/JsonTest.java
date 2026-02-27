package model.persistence;

import model.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class JsonTest {

    // EFFECTS: verifies the car's name matches
    protected void checkCar(String name, Car car) {
        assertEquals(name, car.getName());
    }

    // EFFECTS: verifies the chassis stats match
    protected void checkChassis(String name, int w, double drag, int tire, Chassis c) {
        assertEquals(name, c.getName());
        assertEquals(w, c.getWeight());
        assertEquals(drag, c.getDragCoefficient());
        assertEquals(tire, c.getMaxTireWidth());
    }

    // EFFECTS: verifies the engine stats match
    protected void checkEngine(String name, int hp, int w, int red, int cyl, Engine e) {
        assertEquals(name, e.getName());
        assertEquals(hp, e.getBaseHorsepower());
        assertEquals(w, e.getWeight());
        assertEquals(red, e.getRedline());
        assertEquals(cyl, e.getCylinders());
    }

    // EFFECTS: verifies the transmission stats match
    protected void checkTrans(String name, int w, int gears, int shift, double eff, Transmission t) {
        assertEquals(name, t.getName());
        assertEquals(w, t.getWeight());
        assertEquals(gears, t.getGearCount());
        assertEquals(shift, t.getShiftTimeMs());
        assertEquals(eff, t.getEfficiency());
    }
}