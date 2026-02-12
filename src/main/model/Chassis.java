package model;


// Represents the chassis/body of the vehicle. It defines the
    // car's base weight, its aerodynamic properties 
    // (drag coefficient), and physical limitations such as the 
    // maximum allowed tire width.
public class Chassis {

    // REQUIRES: name must be a non-empty string, weight > 0
    // EFFECTS: constructs a chassis
    public Chassis(String name, int weight, double dragCoefficient, int maxTireWidth) {

    }

    public String getName() {
        return null;
    }

    public int getWeight() {
        return 0;
    }

    public double getDragCoefficient() {
        return 1.0;
    }

    public int getMaxTireWidth() {
        return 0;
    }
}
