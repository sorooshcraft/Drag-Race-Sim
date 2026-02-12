package model;

import java.util.List;

// Represents a collection of Cars.
public class Garage {

    // EFFECTS: constructs an empty garage
    public Garage() {

    }

    // MODIFIES: this
    // EFFECTS: adds a car to the garage
    public void addCar(Car c) {
    }

    // REQUIRES: index is within range [0, cars.size() - 1]
    // EFFECTS: returns the car at the given index
    public Car getCar(int index) {
        return null;
    }

    // EFFECTS: returns the number of cars in the garage
    public int getCarCount() {
        return 0;
    }

    // EFFECTS: returns the list of cars (for viewing purposes)
    public List<Car> getCars() {
        return null;
    }
}
