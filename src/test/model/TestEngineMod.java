package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestEngineMod {
    private EngineMod testTurbo;
    private Engine dummyEngine;

    @BeforeEach
    void runBefore() {
        testTurbo = new EngineMod("Big Turbo", 2000, 40, 150);
        dummyEngine = new Engine("Dummy", 100, 100, 100, 4);
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals("Big Turbo", testTurbo.getName());
        assertEquals(2000, testTurbo.getCost());
        assertEquals(40, testTurbo.getWeightChange());
    }

    @Test
    void testGetHorsepowerGain() {
        assertEquals(150, testTurbo.getHorsepowerGain(dummyEngine));
    }

    @Test
    void testGetGripMultiplier() {
        // Engine mods should not affect grip (1.0)
        assertEquals(1.0, testTurbo.getGripMultiplier());
    }
}
