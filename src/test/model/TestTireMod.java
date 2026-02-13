package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestTireMod {
    private TireMod testSlicks;
    private Engine dummyEngine;

    @BeforeEach
    void runBefore() {
        testSlicks = new TireMod("Racing Slicks", 800, 2.5, -30);
        dummyEngine = new Engine("Dummy", 100, 100, 100, 4);
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals("Racing Slicks", testSlicks.getName());
        assertEquals(800, testSlicks.getCost());
        assertEquals(-30, testSlicks.getWeightChange());
    }

    @Test
    void testCalculateHorsepowerGain() {
        // Tires should add 0 HP
        assertEquals(0, testSlicks.getHorsepowerGain(dummyEngine));
    }

    @Test
    void testCalculateGripMultiplier() {
        assertEquals(2.5, testSlicks.getGripMultiplier());
    }
}
