package model;

import org.json.JSONObject;

// Represents bodywork modifications (e.g., Carbon Fiber Hood, Spoilers).
// These parts primarily affect Weight and Grip (aerodynamic downforce).
public class BodyPanelMod extends Modification {

    private double aeroGripMultiplier;

    // REQUIRES: name has non-zero length, cost >= 0, aeroGripMultiplier >= 0.1
    // EFFECTS: constructs a body panel mod. 
    //          weightChange can be negative (for weight reduction).
    public BodyPanelMod(String name, int cost, int weightChange, double aeroGripMultiplier) {
        super(name, cost, weightChange);
        this.aeroGripMultiplier = aeroGripMultiplier;
    }

    // EFFECTS: returns 0 (Body panels don't add horsepower)
    @Override
    public int getHorsepowerGain(Engine engine) {
        return 0;
    }

    // EFFECTS: returns the aerodynamic grip multiplier
    @Override
    public double getGripMultiplier() {
        return aeroGripMultiplier;
    }

    // EFFECTS: returns this body panel modification as a JSON object.
    @Override
    public JSONObject toJson() {
        JSONObject json = super.toJson();
        json.put("type", "BodyPanelMod");
        json.put("aeroGripMultiplier", aeroGripMultiplier);
        return json;
    }
}