package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BodyPanelModTest {

    private BodyPanelMod carbonHood;
    private BodyPanelMod hugeSpoiler;
    private Engine dummyEngine;

    @BeforeEach
    void runBefore() {
        carbonHood = new BodyPanelMod("Carbon Hood", 1000, -20, 1.0);
        hugeSpoiler = new BodyPanelMod("GT Wing", 500, 10, 1.2);
        dummyEngine = new Engine("Dummy", 100, 100, 100, 4);
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals("Carbon Hood", carbonHood.getName());
        assertEquals(1000, carbonHood.getCost());
        
        assertEquals(-20, carbonHood.getWeightChange());
        assertEquals(10, hugeSpoiler.getWeightChange());
    }

    @Test
    void testCalculateHorsepowerGain() {
        assertEquals(0, carbonHood.getHorsepowerGain(dummyEngine));
        assertEquals(0, hugeSpoiler.getHorsepowerGain(dummyEngine));
    }

    @Test
    void testCalculateGripMultiplier() {
        assertEquals(1.0, carbonHood.getGripMultiplier());
        assertEquals(1.2, hugeSpoiler.getGripMultiplier());
    }
}