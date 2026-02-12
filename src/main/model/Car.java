package model;

import java.util.List;

// Represents a fully assembled vehicle with a specificed chassis, engine, transmission,
    // and a list of installed modifications. This class is responsible for aggregating
    // the stats from all its components to calculate total performance metrics like
    // horsepower, weight, and quarter-mile time.
public class Car {

    // REQUIRES: carName has a non-zero length
    // EFFECTS: variables are stored and initialized. 
    public Car(String carName, Chassis chassis, Engine engine, Transmission transmission) {

    }

    // EFFECTS: returns the total horsepower of this car object, 
    //          including power gains from the engine, 
    //          transmission, and mods
    public int calculateHorsepower() {
        return 0; //stub
    }

    // EFFECTS: returns the total weight of this car object 
    //          including weight from the chassis, engine, 
    //          transmission and mods
    public int calculateWeight() {
        return 0; //stub
    }

// EFFECTS: returns a grip score based on tires, chassis, and weight
    public double calculateTotalGrip() {
        return 1.0;
    }

    // EFFECTS: returns the quarter mile time of this vehicle in seconds.
    public double calculateQuarterMileTime() {
        return 0.0; //stub
    }

    // MODIFIES: this
    // EFFECTS: adds a mod to the car's list of mods
    public boolean addMod(Modification mod) {
        return false;
    }
    

    // MODIFIES: this
    // REQUIRES: name has a non-zero length
    // EFFECTS: sets the name of the car
    public Boolean setName(String name) {
        return false;
    }
    

    // MODIFIES: this
    // EFFECTS: changes the engine of the car
    public void setEngine(Engine engine) {
    }

    // MODIFIES: this
    // EFFECTS: changes the transmission of the car
    public void setTransmission(Transmission transmission) {
    }
    
    // MODIFIES: this
    // EFFECTS: changes the chassis of the car
    public void setChassis(Chassis chassis) {
    }

    
    List<Modification> getMods() {
        return null;
    }

    String getName() {
        return null;
    }
    
    Engine getEngine() {
        return null;
    }

    Transmission getTransmission() {
        return null;
    }

    Chassis getChassis() {
        return null;
    }

}