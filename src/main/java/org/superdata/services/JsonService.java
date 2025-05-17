package org.superdata.services;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import org.springframework.stereotype.Service;
import org.superdata.models.Json;
import org.superdata.utils.DataUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class JsonService {
    /*
     This method has a function to transform the json to Csv.
     */
    public File jsonToCsv(String content) throws IOException {
        Json json = new Json.Builder().setContent(content).build();
        CSVReader csvReader = json.decodeToCSV();
        File tempFile = null;

        try {
            tempFile = File.createTempFile("temp", ".csv");
        } catch (IOException e) {
            throw new RuntimeException("Error creating temp file", e);
        }

        try (CSVWriter writer = new CSVWriter(new FileWriter(tempFile))) {
            List<String[]> data = csvReader.readAll();
            writer.writeAll(data);
        } catch (CsvException e) {
            throw new RuntimeException("Error writing csv file", e);
        }

        return tempFile;
    }
}
