package ru.otus.homework.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.otus.homework.entity.Question;
import ru.otus.homework.exeption.QuestionNotFoundException;
import ru.otus.homework.mapper.CsvMapper;
import ru.otus.homework.service.FileReaderCsv;

import java.util.ArrayList;
import java.util.List;

@Repository
public class QuestionsRepositoryCsv implements QuestionsRepository {
    private final FileReaderCsv questionFileReader;

    private final CsvMapper<Question> questionCsvMapper;

    private final String filePath;

    public QuestionsRepositoryCsv(FileReaderCsv questionFileReader, CsvMapper<Question> questionCsvMapper,
                                  @Value("${test.file}") String filePath) {
        this.questionFileReader = questionFileReader;
        this.questionCsvMapper = questionCsvMapper;
        this.filePath = filePath;
    }

    @Override
    public List<Question> getQuestionList() {
        List<Question> questionList = new ArrayList<>();

        List<String []> rawDataList;
        rawDataList = questionFileReader.readRawData(filePath);

        for (String[] rawString: rawDataList) {
            if (rawString[0].isEmpty()) {
                throw new QuestionNotFoundException("string number = "+ rawDataList.indexOf(rawString)+1);
            }
            questionList.add(questionCsvMapper.toEntity(rawString));
        }

        return questionList;
    }
}
