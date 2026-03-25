package model;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

import java.util.ArrayList;

// Represents a fully assembled vehicle with a specificed chassis, engine, transmission,
    // and a list of installed modifications. This class is responsible for aggregating
    // the stats from all its components to calculate total performance metrics like
    // horsepower, weight, and quarter-mile time.
public class Car implements Writable {

    private String name;
    private Chassis chassis;
    private Engine engine;
    private Transmission transmission;
    private List<Modification> mods;

    private EventLog eventLog;

    // REQUIRES: carName has a non-zero length
    // EFFECTS: variables are stored and initialized. 
    public Car(String carName, Chassis chassis, Engine engine, Transmission transmission) {
        this.name = carName;
        this.chassis = chassis;
        this.engine = engine;
        this.transmission = transmission;
        this.mods = new ArrayList<>();

        eventLog = EventLog.getInstance();
    }

    // EFFECTS: returns the total horsepower of this car object, 
    //          including power gains from the engine, 
    //          transmission, and mods
    public int calculateHorsepower() {
        int totalHP = engine.getBaseHorsepower();
        for (Modification m : mods) {
            totalHP += m.getHorsepowerGain(engine);
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
            totalGrip *= m.getGripMultiplier();
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

        eventLog.logEvent(new Event("Modification '" + mod.getName() + "' added to car '" + name + "'"));
    }
    

    // MODIFIES: this
    // REQUIRES: name has a non-zero length
    // EFFECTS: sets the name of the car
    public void setName(String name) {
        this.name = name;

        eventLog.logEvent(new Event("Car renamed to '" + name + "'"));
    }
    

    // MODIFIES: this
    // EFFECTS: changes the engine of the car
    public void setEngine(Engine engine) {
        this.engine = engine;
        eventLog.logEvent(new Event("Engine set to '" + engine.getName() + "' for car '" + name + "'"));    
    }

    // MODIFIES: this
    // EFFECTS: changes the transmission of the car
    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
        eventLog.logEvent(new Event("Transmission changed for car '" + name + "'"));

    }
    
    // MODIFIES: this
    // EFFECTS: changes the chassis of the car
    public void setChassis(Chassis chassis) {
        this.chassis = chassis;
        eventLog.logEvent(new Event("Chassis changed for car '" + name + "'"));
    }

    
    public List<Modification> getMods() {
        return mods;
    }

    public String getName() {
        return name;
    }
    
    public Engine getEngine() {
        return engine;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public Chassis getChassis() {
        return chassis;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("chassis", chassis.toJson());
        json.put("engine", engine.toJson());
        json.put("transmission", transmission.toJson());
        json.put("mods", modsToJson());
        return json;
    }

    // EFFECTS: returns this car as a JSON object.
    private JSONArray modsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Modification m : mods) {
            jsonArray.put(m.toJson());
        }
        return jsonArray;
    }
}