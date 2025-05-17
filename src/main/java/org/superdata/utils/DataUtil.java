package org.superdata.utils;

import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import org.superdata.exceptions.InvalidDataException;

public abstract class DataUtil {
    /*
        this function go to valid case content is a json
     */
    public static void isValidJson(String json) {
        if (json == null || json.isBlank()) {
            throw new InvalidDataException("Json is null or empty.");
        }
        try {
            JsonParser.parseString(json);
        } catch (JsonSyntaxException e) {
            throw new InvalidDataException("Json syntax error: " + e.getMessage());
        }
    }
}
