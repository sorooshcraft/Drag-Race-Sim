package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the Event class
 */
public class EventTest {
    private Event event;
    private Date date;
    
    
    @BeforeEach
    public void runBefore() {
        event = new Event("Car added");   // (1)
        date = Calendar.getInstance().getTime();   // (2)
    }
    
    @Test
    public void testEvent() {
        assertEquals("Car added", event.getDescription());
        assertEquals(date.getTime() / 1000, event.getDate().getTime() / 1000);
    }

    @Test
    public void testToString() {
        assertEquals(date.toString() + "\n" + "Car added", event.toString());
    }
}