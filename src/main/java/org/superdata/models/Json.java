package org.superdata.models;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.opencsv.CSVReader;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Json extends SuperDataImpl<String> {
    private final JsonElement jsonElement;

    public Json(String content) {
        super(content, TypeData.JSON);
        this.jsonElement = JsonParser.parseString(content);
    }

    public JsonElement getJsonElement() {
        return jsonElement;
    }

    public CSVReader decodeToCSV(){
        if (!this.jsonElement.isJsonArray()) {
            throw new IllegalStateException("Json is not an array");
        }
        JsonArray jsonArray = jsonElement.getAsJsonArray();

        if (jsonArray.isEmpty()) {
            throw new IllegalStateException("Array JSON está vazio.");
        }

        JsonObject first = jsonArray.get(0).getAsJsonObject();
        Set<String> headers = first.keySet();
        StringBuilder csvBuilder = new StringBuilder();
        csvBuilder.append(String.join(",", headers)).append("\n");
        for (JsonElement el : jsonArray) {
            JsonObject obj = el.getAsJsonObject();
            List<String> values = new ArrayList<>();

            for (String header : headers) {
                JsonElement value = obj.get(header);
                values.add(value != null ? value.getAsString() : "");
            }

            csvBuilder.append(String.join(",", values)).append("\n");
        }

        return new CSVReader(new StringReader(csvBuilder.toString()));
    }

}
