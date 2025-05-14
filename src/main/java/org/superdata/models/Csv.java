package org.superdata.models;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.util.List;

public class Csv extends SuperDataImpl<Reader> {
    private final CSVReader reader;

    public Csv(Reader content) {
        super(content, TypeData.CSV);
        this.reader = new CSVReader(content);
    }

    public JsonArray decodeToJson() throws IOException, CsvException {
        List<String[]> strings = reader.readAll();
        JsonArray jsonArray = new JsonArray();


        String[] titles = strings.get(0);
        strings.remove(0);

        for (String[] strings1 : strings) {
            JsonObject jsonObject = new JsonObject();
            for (int j = 0; j < titles.length; j++) {
                System.out.println(titles[j]);
                System.out.println(strings1[j]);
                jsonObject.addProperty(titles[j], strings1[j]);
            }
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    public static class Builder {
        public Reader content;

        public Csv.Builder setContent(Reader content) {
            this.content = content;
            return this;
        }

        public Csv build() {
            return new Csv(content);
        }
    }
}
