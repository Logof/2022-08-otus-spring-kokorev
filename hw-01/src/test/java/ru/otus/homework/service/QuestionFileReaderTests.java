package ru.otus.homework.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.homework.hw01.service.QuestionFileReader;
import ru.otus.homework.hw01.service.QuestionFileReaderImpl;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class QuestionFileReaderTests {

    @DisplayName("Проверка корректности чтения данных из файла CSV")
    @Test
    public void testingCorrectnessReadingDataFromCSVFile() {
        QuestionFileReader questionFileReader = new QuestionFileReaderImpl();
        try {
            List<String[]> rawStringExpected = questionFileReader.readRawData("/test.csv");
            String [] firstRawStringExpected = rawStringExpected.get(0);

            String[] firstRawStringActual = {"Question 1", "Answer 1", "TRUE", "Answer 2","","Answer 3",""};

            assertArrayEquals(firstRawStringExpected, firstRawStringActual);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
