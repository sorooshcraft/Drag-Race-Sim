package model;

// Represents a part which modifies the car to add power 
    // (e.g., turbochargers, intakes, ECU tunes). Its primary
    //  function is to increase the vehicle's total 
    // horsepower calculation.

public class EngineMod extends Modification {

    // REQUIRES: name has a non-zero length and cost >= 0 and hpBoost > 0
    // EFFECTS: constructs an engine modification 
    public EngineMod(String name, int cost, int weightChange, int horsepowerBoost) {
        super(name, cost, weightChange);
    }


    // EFFECTS: returns the amount of horsepower this mod adds to the given engine
    @Override
    public int calculateHorsepowerGain(Engine engine) {
        return 0;
    }
    
    // EFFECTS: returns the grip multiplier provided by this mod (1.0 is neutral)
    @Override
    public double calculateGripMultiplier() {
        return 1.0;
    }

}
