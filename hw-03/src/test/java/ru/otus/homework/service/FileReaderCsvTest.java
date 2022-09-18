package ru.otus.homework.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@SpringBootTest
@DisplayName("Чтение CSV файлов ")
public class FileReaderCsvTest {
    @DisplayName("корректно читаеься CSV файл")
    @Test
    public void shouldHaveCorrectReadingCsvFile() {
        FileReaderCsv fileReaderCsv = new FileReaderCsv();

        List<String[]> fileDataExpected = fileReaderCsv.readRawData("/csv/test.csv");

        List<String[]> fileDataActual = new ArrayList<>();
        fileDataActual.add(new String[]{"Question 1", "ENTER_ANSWER", "hello world", "true", "", "", "", "", ""});
        fileDataActual.add(new String[]{"Question 2", "CHOICE_ANSWERS", "Answer 1", "TRUE", "Answer 2", "", "Answer 3", "true"});

        assertArrayEquals(fileDataExpected.get(0), fileDataActual.get(0));
    }
}
