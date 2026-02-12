package model;

// Represents different tire types and compounds 
    // (e.g., street tires, drag radials, slicks). Its
    // primary function is to provide a grip multiplier 
    // to improve traction during acceleration simulation.

public class TireMod extends Modification {

    // REQUIRES: name has non-zero length and gripMultiplier >= 1.0
    // EFFECTS: constructs a tire modification. 
    public TireMod(String name, int cost, double gripMultiplier, int weightChange) {
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
        return 0;
    }
}
