package org.superdata.services.json;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import org.springframework.stereotype.Service;
import org.superdata.models.Json;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class JsonService {
    /*
     This method has a function to transform the json to Csv.
     */
    public File jsonToCsv(String content) throws IOException, CsvException {
        Json json = new Json.Builder().setContent(content).build();
        CSVReader strings = json.decodeToCSV();
        File tempFile = File.createTempFile("temp", ".csv");
        try (CSVWriter writer = new CSVWriter(new FileWriter(tempFile))) {
            List<String[]> data = strings.readAll();
            writer.writeAll(data);
        }
        return tempFile;
    }
}
