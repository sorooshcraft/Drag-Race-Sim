package persistence;

import java.io.IOException;

import model.Garage;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads garage from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Garage read() throws IOException {
        return null; // stub
    }
}