package model.persistence;

import model.*;
import persistence.*;
import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExcludeFromJacocoGeneratedReport
class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyGarage() {
        try {
            Garage g = new Garage();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyGarage.json");
            writer.open();
            writer.write(g);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyGarage.json");
            g = reader.read();
            assertEquals(0, g.getCarCount());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralGarage() {
        try {
            Garage g = new Garage();
            g.addCar(buildTestCar("Car1"));
            g.addCar(buildTestCar("Car2"));

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralGarage.json");
            writer.open();
            writer.write(g);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralGarage.json");
            g = reader.read();
            List<Car> cars = g.getCars();
            assertEquals(2, cars.size());
            checkCar("Car1", cars.get(0));
            checkCar("Car2", cars.get(1));
            
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    // Helper to build a car for testing
    private Car buildTestCar(String name) {
        Chassis c = new Chassis("C", 1000, 0.3, 200);
        Engine e = new Engine("E", 200, 200, 5000, 4);
        Transmission t = new Transmission("T", 100, 5, 100, 0.9);
        Car car = new Car(name, c, e, t);
        car.addMod(new EngineMod("Turbo", 1000, 50, 100));
        return car;
    }
}