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
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

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
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(g);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            g = reader.read();
            assertEquals(0, g.getCarCount());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Garage g = new Garage();

            Chassis chassis = new Chassis("C", 1000, .3, 200);
            Engine engine = new Engine("E", 200, 200, 5000, 4);
            Transmission transmission = new Transmission("T", 100, 5, 100, .9);

            Car car = new Car("FastCar", chassis, engine, transmission);

            Modification engineMod = new EngineMod("Turbo", 1000, 50, 100);
            Modification tireMod = new TireMod("Slicks", 500, 10, 2.0);
            Modification bodyPanelMod = new BodyPanelMod("Wing", 300, 15, 1.1);

            car.addMod(engineMod);
            car.addMod(tireMod);
            car.addMod(bodyPanelMod);

            g.addCar(car);
            

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(g);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            g = reader.read();
            Car c = g.getCar(0);
            assertEquals("FastCar", c.getName());

            assertEquals("C", c.getChassis().getName());
            assertEquals(1000, c.getChassis().getWeight());
            assertEquals(.3, c.getChassis().getDragCoefficient());
            assertEquals(200, c.getChassis().getMaxTireWidth());

            assertEquals("E", c.getEngine().getName());
            assertEquals(200, c.getEngine().getBaseHorsepower());
            assertEquals(200, c.getEngine().getWeight());
            assertEquals(5000, c.getEngine().getRedline());
            assertEquals(4, c.getEngine().getCylinders());

            assertEquals("T", c.getTransmission().getName());
            assertEquals(100, c.getTransmission().getWeight());
            assertEquals(5, c.getTransmission().getGearCount());
            assertEquals(100, c.getTransmission().getShiftTimeMs());
            assertEquals(.9, c.getTransmission().getEfficiency());

            List<Modification> mods = c.getMods();

            assertEquals("Turbo", mods.get(0).getName());
            assertEquals(1000, mods.get(0).getCost());
            assertEquals(50, mods.get(0).getWeightChange());
            assertEquals(100, mods.get(0).getHorsepowerGain(c.getEngine()));

            assertEquals("Slicks", mods.get(1).getName());
            assertEquals(500, mods.get(1).getCost());
            assertEquals(10, mods.get(1).getWeightChange());
            assertEquals(2.0, mods.get(1).getGripMultiplier());

            assertEquals("Wing", mods.get(2).getName());
            assertEquals(300, mods.get(2).getCost());
            assertEquals(15, mods.get(2).getWeightChange());
            assertEquals(1.1, mods.get(2).getGripMultiplier());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}