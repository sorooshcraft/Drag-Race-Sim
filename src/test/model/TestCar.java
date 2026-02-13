package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestCar {
    private Car testCar;
    private Chassis testChassis;
    private Engine testEngine;
    private Transmission testTransmission;
    private BodyPanelMod testSpoiler;

    
    // Sample Modifications
    private EngineMod testTurbo;
    private TireMod testSlicks;

    @BeforeEach
    void runBefore() {
        testChassis = new Chassis("Tube Frame", 1000, 0.35, 305);
        testEngine = new Engine("Big Block V8", 400, 500, 7000, 8);
        testTransmission = new Transmission("Race Auto", 150, 4, 100, 0.90);
        testCar = new Car("Project X", testChassis, testEngine, testTransmission);
        testTurbo = new EngineMod("Huge Turbo", 2000, 50, 200);
        testSlicks = new TireMod("Drag Slicks", 500, 10, 2.0);
        testSpoiler = new BodyPanelMod("Big Wing", 300, 15, 1.1);

    }

    @Test
    void testConstructor() {
        assertEquals("Project X", testCar.getName());
        assertEquals(testChassis, testCar.getChassis());
        assertEquals(testEngine, testCar.getEngine());
        assertEquals(testTransmission, testCar.getTransmission());
        
        assertNotNull(testCar.getMods());
        assertTrue(testCar.getMods().isEmpty());
    }

    @Test
    void testSetName() {
        testCar.setName("The Beast");
        assertEquals("The Beast", testCar.getName());
    }

    @Test
    void testSetChassis() {
        Chassis newChassis = new Chassis("Carbon Fiber", 500, 0.30, 315);
        testCar.setChassis(newChassis);
        assertEquals(newChassis, testCar.getChassis());
    }

    @Test
    void testSetEngine() {
        Engine newEngine = new Engine("V12", 700, 600, 8000, 12);
        testCar.setEngine(newEngine);
        assertEquals(newEngine, testCar.getEngine());
    }

    @Test
    void testSetTransmission() {
        Transmission newTrans = new Transmission("Manual", 100, 6, 200, 0.95);
        testCar.setTransmission(newTrans);
        assertEquals(newTrans, testCar.getTransmission());
    }

    @Test
    void testAddMod() {
        testCar.addMod(testTurbo);
        
        List<Modification> mods = testCar.getMods();
        assertEquals(1, mods.size());
        assertEquals(testTurbo, mods.get(0));
    }

    @Test
    void testCalculateHorsepower() {
        assertEquals(400, testCar.calculateHorsepower());

        testCar.addMod(testTurbo);
        assertEquals(600, testCar.calculateHorsepower());
    }

    @Test
    void testCalculateWeight() {
        assertEquals(1650, testCar.calculateWeight());
        testCar.addMod(testTurbo);
        assertEquals(1700, testCar.calculateWeight());
        testCar.addMod(testSlicks);
        assertEquals(1710, testCar.calculateWeight());
        testCar.addMod(testSpoiler);
        assertEquals(1725, testCar.calculateWeight());
    }

    @Test
    void testCalculateTotalGrip() {
        assertEquals(1.0, testCar.calculateTotalGrip(), 0.001);

        testCar.addMod(testSlicks);
        assertEquals(2.0, testCar.calculateTotalGrip(), 0.001);

        testCar.addMod(testSpoiler);
        assertEquals(2.2, testCar.calculateTotalGrip(), 0.001);
    }

    @Test
    void testCalculateQuarterMileTime() {
        double expectedBase = 5.825 * Math.pow(1650.0 / 400.0, 0.333);
        assertEquals(expectedBase, testCar.calculateQuarterMileTime(), 0.0001);

        testCar.addMod(testTurbo);
        double expectedTurbo = 5.825 * Math.pow(1700.0 / 600.0, 0.333);
        assertEquals(expectedTurbo, testCar.calculateQuarterMileTime(), 0.0001);

        testCar.addMod(testSlicks);
        double expectedSlicksRaw = 5.825 * Math.pow(1710.0 / 600.0, 0.333);
        double expectedSlicksFinal = expectedSlicksRaw / Math.sqrt(2.0);
        assertEquals(expectedSlicksFinal, testCar.calculateQuarterMileTime(), 0.0001);
    }
}
