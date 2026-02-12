package model;


// Represents the vehicle's transmission system. It defines 
    // performance characteristics such as the number of
    // gears, the time it takes to shift (in milliseconds),
    // and the drivetrain's power transfer efficiency.

public class Transmission {

    // REQUIRES: name must be non-empty and weight > 0 and efficiency between 0 and 1
    // EFFECTS: constructs a transmission
    public Transmission(String name, int weight, int gearCount, int shiftTimeMs, double efficiency) {
    }

    public String getName() {
        return null;
    }

    public int getWeight() {
        return 0;
    }

    public int getGearCount() {
        return 0;
    }

    public int getShiftTimeMs() {
        return 0;
    }

    public double getEfficiency() {
        return 0.0;
    }
}
