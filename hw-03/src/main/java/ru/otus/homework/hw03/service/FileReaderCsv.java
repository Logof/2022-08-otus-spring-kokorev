package ru.otus.homework.hw03.service;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.springframework.stereotype.Component;
import ru.otus.homework.hw03.exeption.ErrorReadingFileException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class FileReaderCsv implements FileReader {

    @Override
    public List<String[]> readRawData(String filePath) throws ErrorReadingFileException {
        if (filePath == null) {
            throw new ErrorReadingFileException("Empty filename or the file does not exist at the specified path");
        }

        InputStream inputStream = getClass().getResourceAsStream(filePath);

        if (inputStream == null) {
            throw new ErrorReadingFileException("Failed to read file");
        }

        CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build();

        try (InputStreamReader streamReader =
                     new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
            try (CSVReader csvReader = new CSVReaderBuilder(streamReader).withCSVParser(csvParser).build()) {
                return csvReader.readAll();
            } catch (CsvException e) {
                throw new ErrorReadingFileException(e.getMessage());
            }
        } catch (IOException e) {
            throw new ErrorReadingFileException(e.getMessage());
        }
    }
}
