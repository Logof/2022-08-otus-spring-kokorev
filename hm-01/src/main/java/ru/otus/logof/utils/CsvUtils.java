package ru.otus.logof.utils;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import ru.otus.logof.entity.Question;
import ru.otus.logof.exception.QuestionNotFoundException;
import ru.otus.logof.mapper.CsvMapper;
import ru.otus.logof.mapper.QuestionCsvMapper;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CsvUtils {

    private static List<String[]> readRawDataFromCSVFile(Path filePath) throws IOException, CsvException {
        if (filePath == null) {
            return null;
        }

        CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build();

        try (Reader reader = Files.newBufferedReader(filePath)) {
            try (CSVReader csvReader = new CSVReaderBuilder(reader).withCSVParser(csvParser).build()) {
                return csvReader.readAll();
            }
        }
    }

    public static List<Question> readTestData(String fileName) throws QuestionNotFoundException {
        if (fileName == null || fileName.isEmpty()) {
            throw new QuestionNotFoundException(fileName + " not found");
        }

        Path filePath = Paths.get(CsvUtils.class.getResource(fileName).getPath());

        try {
            List<String[]> rawData = readRawDataFromCSVFile(filePath);
            List<Question> questionList = new ArrayList<>();
            CsvMapper questionMapper = new QuestionCsvMapper();

            for (String[] rawString: rawData) {
                if (rawString[0].isEmpty()) {
                    throw new QuestionNotFoundException("string number = "+ rawData.indexOf(rawString)+1);
                }
                questionList.add(questionMapper.toEntity(rawString));
            }
            return questionList;
        } catch (CsvException | IOException e) {
            throw new QuestionNotFoundException(e.getMessage());
        }
    }
}
