package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the Event class
 */
public class EventTest {
    private Event event;
    private Date date;
    
    @BeforeEach
    public void runBefore() {
        event = new Event("Car added");
        date = Calendar.getInstance().getTime();
    }
    
    @Test
    public void testEvent() {
        assertEquals("Car added", event.getDescription());
        assertEquals(date.getTime() / 1000, event.getDate().getTime() / 1000);
    }

    @Test
    public void testToString() {
        assertEquals(event.getDate().toString() + "\n" + "Car added", event.toString());
    }

    @Test
    public void testEqualsSameObject() {
        assertTrue(event.equals(event));
    }

    @Test
    public void testEqualsNull() {
        assertFalse(event.equals(null));
    }

    @Test
    public void testEqualsDifferentClass() {
        assertFalse(event.equals("I am a string, not an Event"));
    }

    @Test
    public void testEqualsDifferentDescription() {
        Event differentEvent = new Event("Car removed");
        assertFalse(event.equals(differentEvent));
    }

    @Test
    public void testEqualsDifferentDate() throws InterruptedException {
        Event firstEvent = new Event("Same description");
        
        Thread.sleep(10); 
        
        Event secondEvent = new Event("Same description");
        
        assertFalse(firstEvent.equals(secondEvent));
    }

    @Test
    public void testHashCode() {
        int expectedHash = 13 * event.getDate().hashCode() + event.getDescription().hashCode();
        assertEquals(expectedHash, event.hashCode());
    }
}