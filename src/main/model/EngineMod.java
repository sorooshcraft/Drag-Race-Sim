package model;

import org.json.JSONObject;

// Represents a part which modifies the car to add power 
    // (e.g., turbochargers, intakes, ECU tunes). Its primary
    //  function is to increase the vehicle's total 
    // horsepower calculation.

public class EngineMod extends Modification {
    private int horsepowerBoost;

    // REQUIRES: name has a non-zero length and cost >= 0 and hpBoost > 0
    // EFFECTS: constructs an engine modification 
    public EngineMod(String name, int cost, int weightChange, int horsepowerBoost) {
        super(name, cost, weightChange);
        this.horsepowerBoost = horsepowerBoost;
    }


    // EFFECTS: returns the amount of horsepower this mod adds to the given engine
    @Override
    public int getHorsepowerGain(Engine engine) {
        return horsepowerBoost;
    }
    
    // EFFECTS: returns the grip multiplier provided by this mod (1.0 is neutral)
    @Override
    public double getGripMultiplier() {
        return 1.0;
    }


    // EFFECTS: returns this engine modification as a JSON object.
    @Override
    public JSONObject toJson() {
        JSONObject json = super.toJson();
        json.put("type", "EngineMod");
        json.put("horsepowerBoost", horsepowerBoost);
        return json;
    }

}
