package model;

import org.json.JSONObject;

// Represents different tire types and compounds 
    // (e.g., street tires, drag radials, slicks). Its
    // primary function is to provide a grip multiplier 
    // to improve traction during acceleration simulation.

public class TireMod extends Modification {

    private double gripMultiplier;

    // REQUIRES: name has non-zero length and gripMultiplier >= 1.0
    // EFFECTS: constructs a tire modification. 
    public TireMod(String name, int cost, int weightChange, double gripMultiplier) {
        super(name, cost, weightChange);
        this.gripMultiplier = gripMultiplier;
    }

    // EFFECTS: returns the amount of horsepower this mod adds to the given engine
    @Override
    public int getHorsepowerGain(Engine engine) {
        return 0;
    }

    // EFFECTS: returns the grip multiplier provided by this mod (1.0 is neutral)
    @Override
    public double getGripMultiplier() {
        return gripMultiplier;
    }

    // EFFECTS: returns this tire modification as a JSON object.
    @Override
    public JSONObject toJson() {
        JSONObject json = super.toJson();
        json.put("type", "TireMod");
        json.put("gripMultiplier", gripMultiplier);
        return json;
    }
}
