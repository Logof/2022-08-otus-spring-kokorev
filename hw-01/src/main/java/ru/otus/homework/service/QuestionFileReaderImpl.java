package ru.otus.homework.service;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class QuestionFileReaderImpl implements QuestionFileReader {

    @Override
    public List<String[]> readRawData(String filePath) throws IOException {
        if (filePath == null) {
            return null;
        }

        InputStream inputStream = getClass().getResourceAsStream(filePath);
        if (inputStream == null) {
            return null;
        }

        CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build();

        try (InputStreamReader streamReader =
                     new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
            try (CSVReader csvReader = new CSVReaderBuilder(streamReader).withCSVParser(csvParser).build()) {
                return csvReader.readAll();
            } catch (CsvException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
