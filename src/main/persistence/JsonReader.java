package persistence;

import model.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.json.*;

// Represents a reader that reads garage from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads garage from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Garage read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGarage(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses garage from JSON object and returns it
    private Garage parseGarage(JSONObject jsonObject) {
        Garage g = new Garage();
        addCars(g, jsonObject);
        return g;
    }

    // MODIFIES: g
    // EFFECTS: parses cars from JSON object and adds them to garage
    private void addCars(Garage g, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("cars");
        for (Object json : jsonArray) {
            JSONObject nextCar = (JSONObject) json;
            addCar(g, nextCar);
        }
    }

    // MODIFIES: g
    // EFFECTS: parses individual car from JSON object and adds it to garage
    private void addCar(Garage g, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        
        JSONObject chassisJson = jsonObject.getJSONObject("chassis");
        Chassis c = new Chassis(chassisJson.getString("name"), chassisJson.getInt("weight"),
                chassisJson.getDouble("dragCoefficient"), chassisJson.getInt("maxTireWidth"));
        
        JSONObject engineJson = jsonObject.getJSONObject("engine");
        Engine e = new Engine(engineJson.getString("name"), engineJson.getInt("baseHorsepower"),
                engineJson.getInt("weight"), engineJson.getInt("redline"), engineJson.getInt("cylinders"));
        
        JSONObject transJson = jsonObject.getJSONObject("transmission");
        Transmission t = new Transmission(transJson.getString("name"), transJson.getInt("weight"),
                transJson.getInt("gearCount"), transJson.getInt("shiftTimeMs"), 
                transJson.getDouble("efficiency"));
        
        Car car = new Car(name, c, e, t);
        addMods(car, jsonObject.getJSONArray("mods"));
        g.addCar(car);
    }

    // MODIFIES: car
    // EFFECTS: parses mods from JSON array and adds them to car
    private void addMods(Car car, JSONArray jsonArray) {
        for (Object json : jsonArray) {
            JSONObject nextMod = (JSONObject) json;
            addMod(car, nextMod);
        }
    }

    // MODIFIES: car
    // EFFECTS: parses a single mod and adds it to car
    private void addMod(Car car, JSONObject json) {
        String type = json.getString("type");
        String n = json.getString("name");
        int c = json.getInt("cost");
        int w = json.getInt("weightChange");

        if (type.equals("EngineMod")) {
            car.addMod(new EngineMod(n, c, w, json.getInt("horsepowerBoost")));
        } else if (type.equals("TireMod")) {
            car.addMod(new TireMod(n, c, w, json.getDouble("gripMultiplier")));
        } else if (type.equals("BodyPanelMod")) {
            car.addMod(new BodyPanelMod(n, c, w, json.getDouble("aeroGripMultiplier")));
        }
    }
}