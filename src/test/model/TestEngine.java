package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestEngine {
    private Engine testEngine;

    @Test
    void testConstructorAndGetters() {
        testEngine = new Engine("V8 Super", 500, 450, 7000, 8);
        assertEquals("V8 Super", testEngine.getName());
        assertEquals(500, testEngine.getBaseHorsepower());
        assertEquals(450, testEngine.getWeight());
        assertEquals(7000, testEngine.getRedline());
        assertEquals(8, testEngine.getCylinders());
    }
}
