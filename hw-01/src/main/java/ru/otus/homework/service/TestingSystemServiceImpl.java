package ru.otus.homework.service;

import ru.otus.homework.dao.TestInstance;
import ru.otus.homework.entity.Question;
import ru.otus.homework.exception.QuestionNotFoundException;

import java.io.IOException;

public class TestingSystemServiceImpl  implements TestingSystemService {

    private final OutputService outputService;

    private final TestInstance testInstance;

    public TestingSystemServiceImpl(TestInstance testInstance, OutputService outputService) {
        this.testInstance = testInstance;
        this.outputService = outputService;
    }

    @Override
    public void startTesting() throws QuestionNotFoundException {
        try {
            testInstance.readTestData();

            for (Question question : testInstance.getQuestionList()) {
                outputService.messageOutput(question.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
