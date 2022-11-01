package ru.otus.homework.hw01.dao;

import ru.otus.homework.hw01.entity.Question;
import ru.otus.homework.hw01.exception.QuestionNotFoundException;
import ru.otus.homework.hw01.mapper.QuestionCsvMapper;
import ru.otus.homework.hw01.service.QuestionFileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuestionsDaoCsv implements QuestionsDao {

    private final QuestionFileReader questionFileReader;
    private final QuestionCsvMapper questionCsvMapper;

    private final String filePath;

    public QuestionsDaoCsv(QuestionFileReader questionFileReader, QuestionCsvMapper questionCsvMapper, String filePath) {
        this.questionCsvMapper = questionCsvMapper;
        this.questionFileReader = questionFileReader;
        this.filePath = filePath;
    }

    public List<Question> getQuestionList() {
        List<Question> questionList = new ArrayList<>();

        if (filePath == null || filePath.isEmpty()) {
            return questionList;
        }

        List<String []> rawDataList;
        try {
            rawDataList = questionFileReader.readRawData(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (String[] rawString: rawDataList) {
            if (rawString[0].isEmpty()) {
                throw new QuestionNotFoundException("string number = "+ rawDataList.indexOf(rawString)+1);
            }
            questionList.add(questionCsvMapper.toEntity(rawString));
        }

        return questionList;
    }



}
