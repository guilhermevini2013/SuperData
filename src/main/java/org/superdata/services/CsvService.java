package org.superdata.services;

import com.google.gson.JsonArray;
import com.opencsv.exceptions.CsvException;
import org.springframework.stereotype.Service;
import org.superdata.models.Csv;

import java.io.IOException;
import java.io.Reader;

@Service
public class CsvService {
    /*
     This method has a function to transform the Csv to Json.
     */
    public String csvToJson(Reader reader) throws IOException, CsvException {
        Csv csv = new Csv.Builder().setContent(reader).build();
        JsonArray jsonElements = csv.decodeToJson();
        return jsonElements.toString();
    }
}
