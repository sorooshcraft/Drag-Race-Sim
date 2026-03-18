package model;

import org.json.JSONObject;

import persistence.Writable;

// Represents the chassis/body of the vehicle. It defines the
    // car's base weight, its aerodynamic properties 
    // (drag coefficient), and physical limitations such as the 
    // maximum allowed tire width.
public class Chassis implements Writable {

    private String name;
    private int weight;
    private double dragCoefficient;
    private int maxTireWidth;

    // REQUIRES: name must be a non-empty string, weight > 0
    // EFFECTS: constructs a chassis
    public Chassis(String name, int weight, double dragCoefficient, int maxTireWidth) {
        this.name = name;
        this.weight = weight;
        this.dragCoefficient = dragCoefficient;
        this.maxTireWidth = maxTireWidth;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public double getDragCoefficient() {
        return dragCoefficient;
    }

    public int getMaxTireWidth() {
        return maxTireWidth;
    }

    // EFFECTS: returns this chassis as a JSON object.
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("weight", weight);
        json.put("dragCoefficient", dragCoefficient);
        json.put("maxTireWidth", maxTireWidth);
        return json;
    }
}
