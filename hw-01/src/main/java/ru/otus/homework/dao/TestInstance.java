package ru.otus.homework.dao;

import ru.otus.homework.entity.Question;
import ru.otus.homework.exception.QuestionNotFoundException;
import ru.otus.homework.mapper.QuestionCsvMapper;
import ru.otus.homework.service.QuestionFileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestInstance {
    private final List<Question> questionList;

    public List<Question> getQuestionList() {
        return questionList;
    }

    private final QuestionFileReader questionFileReader;
    private final QuestionCsvMapper questionCsvMapper;

    private final String filePath;

    public TestInstance(QuestionFileReader questionFileReader, QuestionCsvMapper questionCsvMapper, String filePath) {
        this.questionCsvMapper = questionCsvMapper;
        this.questionFileReader = questionFileReader;
        this.questionList = new ArrayList<>();
        this.filePath = filePath;
    }

    public void readTestData() throws IOException {
        if (filePath == null || filePath.isEmpty()) {
            throw new IOException(filePath + " not found");
        }

        List<String []> rawDataList = questionFileReader.readRawData(filePath);

        for (String[] rawString: rawDataList) {
            if (rawString[0].isEmpty()) {
                throw new QuestionNotFoundException("string number = "+ rawDataList.indexOf(rawString)+1);
            }
            questionList.add(questionCsvMapper.toEntity(rawString));
        }
    }



}
