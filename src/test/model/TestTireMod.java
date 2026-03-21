package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.json.JSONObject;

public class TestTireMod {
    private TireMod testSlicks;
    private Engine dummyEngine;

    @BeforeEach
    void runBefore() {
        testSlicks = new TireMod("Racing Slicks", 800, 30, 2.5);
        dummyEngine = new Engine("Dummy", 100, 100, 100, 4);
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals("Racing Slicks", testSlicks.getName());
        assertEquals(800, testSlicks.getCost());
        assertEquals(30, testSlicks.getWeightChange());
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

    @Test
    void testToJson() {
        JSONObject json = testSlicks.toJson();
        assertEquals("Racing Slicks", json.getString("name"));
        assertEquals(800, json.getInt("cost"));
        assertEquals(30, json.getInt("weightChange"));
        assertEquals("TireMod", json.getString("type"));
        assertEquals(2.5, json.getDouble("gripMultiplier"));
    }
}