package org.superdata.controllers;

import com.google.gson.JsonArray;
import com.opencsv.exceptions.CsvException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.superdata.services.json.CsvService;
import org.superdata.services.json.JsonService;

import java.io.*;

@RestController
@RequestMapping(value = "/superData")
public class SuperDataController {
    private final JsonService jsonService;
    private final CsvService csvService;

    public SuperDataController(JsonService jsonService, CsvService csvService) {
        this.jsonService = jsonService;
        this.csvService = csvService;
    }

    @PostMapping(value = "/json/to/csv")
    public ResponseEntity<InputStreamResource> jsonToCsv(@RequestBody String content) throws IOException, CsvException {
        File fileCsv = jsonService.jsonToCsv(content);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=export.csv")
                .contentType(MediaType.parseMediaType("text/csv"))
                .contentLength(fileCsv.length())
                .body(new InputStreamResource(new FileInputStream(fileCsv)));
    }

    @PostMapping(value = "/csv/to/json")
    public ResponseEntity<String> csvToJson(@RequestParam("file") MultipartFile file) throws IOException, CsvException {
        Reader reader = new InputStreamReader(file.getInputStream());
        String jsonResult = csvService.csvToJson(reader);
        return ResponseEntity.ok().body(jsonResult);
    }
}
