package model;

import java.util.List;
import java.util.ArrayList;

// Represents a fully assembled vehicle with a specificed chassis, engine, transmission,
    // and a list of installed modifications. This class is responsible for aggregating
    // the stats from all its components to calculate total performance metrics like
    // horsepower, weight, and quarter-mile time.
public class Car {

    private String name;
    private Chassis chassis;
    private Engine engine;
    private Transmission transmission;
    private List<Modification> mods;

    // REQUIRES: carName has a non-zero length
    // EFFECTS: variables are stored and initialized. 
    public Car(String carName, Chassis chassis, Engine engine, Transmission transmission) {
        this.name = carName;
        this.chassis = chassis;
        this.engine = engine;
        this.transmission = transmission;
        this.mods = new ArrayList<>();
    }

    // EFFECTS: returns the total horsepower of this car object, 
    //          including power gains from the engine, 
    //          transmission, and mods
    public int calculateHorsepower() {
        int totalHP = engine.getBaseHorsepower();
        for (Modification m : mods) {
            totalHP += m.calculateHorsepowerGain(engine);
        }
        return totalHP;
    }

    // EFFECTS: returns the total weight of this car object 
    //          including weight from the chassis, engine, 
    //          transmission and mods
    public int calculateWeight() {
        int totalWeight = chassis.getWeight() 
                        + engine.getWeight() 
                        + transmission.getWeight();
        
        for (Modification m : mods) {
            totalWeight += m.getWeightChange();
        }
        return totalWeight;
    }

    // EFFECTS: returns a grip score based on tires, chassis, and weight
    public double calculateTotalGrip() {
        double totalGrip = 1.0;
        for (Modification m : mods) {
            totalGrip *= m.calculateGripMultiplier();
        }
        return totalGrip;
    }

    // EFFECTS: returns the quarter mile time of this vehicle in seconds.
    //          Uses quarter-mile ET estimator. the formula for approximation:
    //          ET = 5.825 * (Weight / HP)^(1/3)
    public double calculateQuarterMileTime() {
        double weight = (double) calculateWeight();
        double hp = (double) calculateHorsepower();

        double powerToWeightFactor = Math.pow(weight / hp, 0.333);
        double baseTime = 5.825 * powerToWeightFactor;

        double grip = calculateTotalGrip();
        if (grip > 1.0) {
            baseTime = baseTime / Math.sqrt(grip); 
        }

        return baseTime;
    }

    // MODIFIES: this
    // EFFECTS: adds a mod to the car's list of mods
    public void addMod(Modification mod) {
        mods.add(mod);

    }
    

    // MODIFIES: this
    // REQUIRES: name has a non-zero length
    // EFFECTS: sets the name of the car
    public void setName(String name) {
        this.name = name;
    }
    

    // MODIFIES: this
    // EFFECTS: changes the engine of the car
    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    // MODIFIES: this
    // EFFECTS: changes the transmission of the car
    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }
    
    // MODIFIES: this
    // EFFECTS: changes the chassis of the car
    public void setChassis(Chassis chassis) {
        this.chassis = chassis;
    }

    
    List<Modification> getMods() {
        return mods;
    }

    String getName() {
        return name;
    }
    
    Engine getEngine() {
        return engine;
    }

    Transmission getTransmission() {
        return transmission;
    }

    Chassis getChassis() {
        return chassis;
    }

}