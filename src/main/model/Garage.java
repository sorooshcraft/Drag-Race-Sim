package model;

import java.util.List;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// Represents a collection of Cars.
public class Garage implements Writable {

    private List<Car> cars;

    // EFFECTS: constructs an empty garage
    public Garage() {
        cars = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a car to the garage
    public void addCar(Car c) {
        cars.add(c);
    }

    // REQUIRES: index is within range [0, cars.size() - 1]
    // EFFECTS: returns the car at the given index
    public Car getCar(int index) {
        return cars.get(index);
    }

    // EFFECTS: returns the number of cars in the garage
    public int getCarCount() {
        return cars.size();
    }

    // EFFECTS: returns the list of cars (for viewing purposes)
    public List<Car> getCars() {
        return cars;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        for (Car c : cars) {
            jsonArray.put(c.toJson());
        }

        json.put("cars", jsonArray);
        return json;
    }

}
