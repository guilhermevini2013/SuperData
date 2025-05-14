package org.superdata.services;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.superdata.services.json.JsonService;

import java.io.*;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
public class JsonServiceTests {
    @InjectMocks
    public JsonService jsonService;

    @Test
    public void shouldConvertAJsonToFileCsvWithCorrectInformation() throws IOException, CsvException {
        String content = """
                [
                    {
                        "nome":"guilherme",
                        "email":"guilherme@gmail.com",
                        "senha":"123456"
                    },
                    {
                        "nome":"Eduardo",
                        "email":"eduardo@gmail.com",
                        "senha":"123456"
                    }
                ]
                """;
        File file = jsonService.jsonToCsv(content);

        Scanner scanner = new Scanner(file);
        String headerFile = scanner.nextLine();
        assertEquals("\"nome\",\"email\",\"senha\"", headerFile);

        String contentFile = scanner.nextLine();
        assertEquals("\"guilherme\",\"guilherme@gmail.com\",\"123456\"", contentFile);

        assertDoesNotThrow(() -> jsonService.jsonToCsv(content));
    }
}
