package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestTransmission {
    private Transmission testTransmission;

    @Test
    void testConstructorAndGetters() {
        testTransmission = new Transmission("QuickShift", 100, 6, 50, 0.95);
        assertEquals("QuickShift", testTransmission.getName());
        assertEquals(100, testTransmission.getWeight());
        assertEquals(6, testTransmission.getGearCount());
        assertEquals(50, testTransmission.getShiftTimeMs());
        assertEquals(0.95, testTransmission.getEfficiency());
    }
}
