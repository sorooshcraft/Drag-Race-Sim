package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestChassis {
    private Chassis testChassis;

    @Test
    void testConstructorAndGetters() {
        testChassis = new Chassis("Carbon Monocoque", 800, 0.25, 335);
        assertEquals("Carbon Monocoque", testChassis.getName());
        assertEquals(800, testChassis.getWeight());
        assertEquals(0.25, testChassis.getDragCoefficient());
        assertEquals(335, testChassis.getMaxTireWidth());
    }
}
