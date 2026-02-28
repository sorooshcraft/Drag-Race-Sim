package persistence;

import org.json.JSONObject;

// Interface for objects that can be converted into a JSON object for data persistence.
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}