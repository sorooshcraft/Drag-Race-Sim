package model;

public abstract class Modification {

    // REQUIRES: a non-zero length string and cost >= 0
    // EFFECTS: creates a modification with given name, cost, and weight change
    public Modification(String name, int cost, int weightChange) {
    }

    // EFFECTS: returns the amount of horsepower this mod adds to the given engine
    public abstract int calculateHorsepowerGain(Engine engine);

    // EFFECTS: returns the grip multiplier provided by this mod (1.0 is neutral)
    public abstract int calculateGripGain();



    public String getName() {
        return null;
    }

    
    public String getCost() {
        return null;
    }


    public String getWeightChange() {
        return null;
    }

}
