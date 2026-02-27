package model.persistence;

import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import java.io.IOException;
import java.util.List;

import persistence.*;
import model.*;

import static org.junit.jupiter.api.Assertions.*;

@ExcludeFromJacocoGeneratedReport
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Garage g = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyGarage() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyGarage.json");
        try {
            Garage g = reader.read();
            assertEquals(0, g.getCarCount());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralGarage() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralGarage.json");
        try {
            Garage g = reader.read();
            List<Car> cars = g.getCars();
            assertEquals(1, cars.size());
            
            Car c = cars.get(0);
            checkCar("FastCar", c);
            checkCarComponents("C", "E", "T", c);
            
            // Verify mods
            assertEquals(3, c.getMods().size());
            assertEquals("Turbo", c.getMods().get(0).getName());
            assertEquals("Slicks", c.getMods().get(1).getName());
            assertEquals("Wing", c.getMods().get(2).getName());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
