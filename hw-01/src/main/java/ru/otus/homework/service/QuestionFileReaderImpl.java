package ru.otus.homework.service;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class QuestionFileReaderImpl implements QuestionFileReader {

    @Override
    public List<String[]> readRawData(String filePath) throws IOException {
        if (filePath == null) {
            return null;
        }

        CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build();

        try (Reader reader = Files.newBufferedReader(Paths.get(getClass().getResource(filePath).getPath()))) {
            try (CSVReader csvReader = new CSVReaderBuilder(reader).withCSVParser(csvParser).build()) {
                return csvReader.readAll();
            } catch (CsvException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
