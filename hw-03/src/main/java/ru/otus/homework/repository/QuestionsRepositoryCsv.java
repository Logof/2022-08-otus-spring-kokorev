package ru.otus.homework.repository;

import org.springframework.stereotype.Repository;
import ru.otus.homework.entity.Question;
import ru.otus.homework.exeption.QuestionNotFoundException;
import ru.otus.homework.mapper.CsvMapper;
import ru.otus.homework.service.FileReader;

import java.util.ArrayList;
import java.util.List;

@Repository
public class QuestionsRepositoryCsv implements QuestionsRepository {
    private final FileReader questionFileReader;

    private final CsvMapper<Question> questionCsvMapper;

    public QuestionsRepositoryCsv(FileReader questionFileReader, CsvMapper<Question> questionCsvMapper) {
        this.questionFileReader = questionFileReader;
        this.questionCsvMapper = questionCsvMapper;
    }

    @Override
    public List<Question> getQuestionList() {
        return getQuestionList(null);
    }

    @Override
    public List<Question> getQuestionList(String filePath) {
        List<Question> questionList = new ArrayList<>();

        List<String[]> rawDataList;
        rawDataList = questionFileReader.readRawData(filePath);

        for (String[] rawString : rawDataList) {
            if (rawString[0].isEmpty()) {
                throw new QuestionNotFoundException("string number = " + rawDataList.indexOf(rawString) + 1);
            }
            questionList.add(questionCsvMapper.toEntity(rawString));
        }

        return questionList;
    }
}
