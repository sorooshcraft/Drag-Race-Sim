package model;

// An abstract class representing a generic aftermarket 
    // part that can be installed on a car. It defines 
    // properties like name, cost, and weight change.
public abstract class Modification {

    protected String name;
    protected int cost;
    protected int weightChange; 

    // REQUIRES: a non-zero length string and cost >= 0
    // EFFECTS: creates a modification with given name, cost, and weight change
    public Modification(String name, int cost, int weightChange) {
        this.name = name;
        this.cost = cost;
        this.weightChange = weightChange;
    }

    // EFFECTS: returns the amount of horsepower this mod adds to the given engine
    public abstract int getHorsepowerGain(Engine engine);

    // EFFECTS: returns the grip multiplier provided by this mod (1.0 is neutral)
    public abstract double getGripMultiplier();

    public String getName() {
        return name;
    }

    
    public int getCost() {
        return cost;
    }


    public int getWeightChange() {
        return weightChange;
    }

}
