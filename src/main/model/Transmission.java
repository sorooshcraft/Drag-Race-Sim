package model;


// Represents the vehicle's transmission system. It defines 
    // performance characteristics such as the number of
    // gears, the time it takes to shift (in milliseconds),
    // and the drivetrain's power transfer efficiency.

public class Transmission {

    private String name;
    private int weight;
    private int gearCount; 
    private int shiftTimeMs; 
    private double efficiency; 

    // REQUIRES: name must be non-empty and weight > 0 and efficiency between 0 and 1
    // EFFECTS: constructs a transmission
    public Transmission(String name, int weight, int gearCount, int shiftTimeMs, double efficiency) {
        this.name = name;
        this.weight = weight;
        this.gearCount = gearCount;
        this.shiftTimeMs = shiftTimeMs;
        this.efficiency = efficiency;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public int getGearCount() {
        return gearCount;
    }

    public int getShiftTimeMs() {
        return shiftTimeMs;
    }

    public double getEfficiency() {
        return efficiency;
    }
}
