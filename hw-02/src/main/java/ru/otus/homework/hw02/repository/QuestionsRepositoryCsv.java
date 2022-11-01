package ru.otus.homework.hw02.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.otus.homework.hw02.entity.Question;
import ru.otus.homework.hw02.exeption.QuestionNotFoundException;
import ru.otus.homework.hw02.mapper.CsvMapper;
import ru.otus.homework.hw02.service.FileReader;

import java.util.ArrayList;
import java.util.List;

@Repository
public class QuestionsRepositoryCsv implements QuestionsRepository {
    private final FileReader questionFileReader;

    private final CsvMapper<Question> questionCsvMapper;

    private final String filePath;

    public QuestionsRepositoryCsv(FileReader questionFileReader, CsvMapper<Question> questionCsvMapper,
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
