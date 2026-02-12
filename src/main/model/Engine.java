package model;


// Represents the vehicle's base engine. It stores fundamental
    // specifications including base horsepower, engine weight,
    // redline RPM, and cylinder count.

public class Engine {

    private String name;
    private int baseHorsepower;
    private int weight;
    private int redline; 
    private int cylinders; 

    // REQUIRES: name is non-empty and hp > 0 and weight > 0
    // EFFECTS: constructs an engine
    public Engine(String name, int baseHorsepower, int weight, int redline, int cylinders) {
        this.name = name;
        this.baseHorsepower = baseHorsepower;
        this.weight = weight;
        this.redline = redline;
        this.cylinders = cylinders;
    }

    public String getName() {
        return name;
    }

    public int getBaseHorsepower() {
        return baseHorsepower;
    }

    public int getWeight() {
        return weight;
    }

    public int getRedline() {
        return redline;

    }
    
    public int getCylinders() {
        return cylinders;
    }
}
