package org.superdata.controllers;

import com.google.gson.JsonArray;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.superdata.models.Csv;
import org.superdata.models.Json;

import java.io.*;
import java.util.List;

@RestController
@RequestMapping(value = "/superData")
public class SuperDataController {

    @PostMapping(value = "/json/to/csv")
    public ResponseEntity<InputStreamResource> jsonToCsv(@RequestBody String content) throws IOException, CsvException {
        Json json = new Json(content);

        CSVReader strings = json.decodeToCSV();
        File tempFile = File.createTempFile("pessoa_", ".csv");

        try (CSVWriter writer = new CSVWriter(new FileWriter(tempFile))) {
            List<String[]> data = strings.readAll();
            writer.writeAll(data);
        }
        InputStreamResource resource = new InputStreamResource(new FileInputStream(tempFile));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=export.csv")
                .contentType(MediaType.parseMediaType("text/csv"))
                .contentLength(tempFile.length())
                .body(resource);
    }

    @PostMapping(value = "/csv/to/json")
    public ResponseEntity<String> csvToJson(@RequestParam("file") MultipartFile file) throws IOException, CsvException {
        Reader reader = new InputStreamReader(file.getInputStream());
        Csv csv = new Csv(reader);
        JsonArray jsonElements = csv.decodeToJson();
        return ResponseEntity.ok().body(jsonElements.toString());
    }
}
