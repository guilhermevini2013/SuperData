package org.superdata.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.superdata.exceptions.InvalidDataException;
import org.superdata.factory.DataFactory;

import java.io.*;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
public class JsonServiceTests {
    @InjectMocks
    public JsonService jsonService;

    @Test
    public void shouldConvertAJsonToFileCsvWithCorrectInformation() throws IOException {
        String content = DataFactory.validContent();
        File file = jsonService.jsonToCsv(content);

        Scanner scanner = new Scanner(file);
        String headerFile = scanner.nextLine();
        assertEquals("\"nome\",\"email\",\"senha\"", headerFile);

        String contentFile = scanner.nextLine();
        assertEquals("\"guilherme\",\"guilherme@gmail.com\",\"123456\"", contentFile);

        assertDoesNotThrow(() -> jsonService.jsonToCsv(content));
    }

    @Test
    public void dontShouldConvertAJsonToFileCsvIfItIsNullOrIncorrectFormatAndThrowException() {
        String content = DataFactory.invalidContent();
        assertThrows(InvalidDataException.class, () -> jsonService.jsonToCsv(content));
        assertThrows(InvalidDataException.class, () -> jsonService.jsonToCsv(null));
        assertThrows(InvalidDataException.class, () -> jsonService.jsonToCsv(""));
    }
}
