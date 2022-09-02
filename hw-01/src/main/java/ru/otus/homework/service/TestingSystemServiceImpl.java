package ru.otus.homework.service;

import ru.otus.homework.dao.Test;
import ru.otus.homework.entity.Question;
import ru.otus.homework.exception.QuestionNotFoundException;

import java.io.IOException;

public class TestingSystemServiceImpl  implements TestingSystemService {

    private OutputService outputService;

    private Test test;

    public TestingSystemServiceImpl(Test test, OutputService outputService) {
        this.test = test;
        this.outputService = outputService;
    }

    @Override
    public void startTesting() throws QuestionNotFoundException {
        String filePath = "/csv/test01.csv";

        try {
            test.readTestData(filePath);

            for (Question question : test.getQuestionList()) {
                outputService.messageOutput(question.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
