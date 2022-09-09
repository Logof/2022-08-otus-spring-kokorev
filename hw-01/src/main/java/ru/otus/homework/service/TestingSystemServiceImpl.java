package ru.otus.homework.service;

import ru.otus.homework.dao.QuestionsDao;
import ru.otus.homework.entity.Question;
import ru.otus.homework.exception.QuestionNotFoundException;

public class TestingSystemServiceImpl  implements TestingSystemService {

    private final OutputService outputService;

    private final QuestionsDao questionsDao;

    public TestingSystemServiceImpl(QuestionsDao questionsDao, OutputService outputService) {
        this.questionsDao = questionsDao;
        this.outputService = outputService;
    }

    @Override
    public void startTesting() throws QuestionNotFoundException {
        for (Question question : questionsDao.getQuestionList()) {
                outputService.messageOutput(question.toString());
        }
    }
}
