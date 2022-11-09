package ru.otus.homework.hw01.service;

import ru.otus.homework.hw01.dao.QuestionsDao;
import ru.otus.homework.hw01.entity.Question;
import ru.otus.homework.hw01.exception.QuestionNotFoundException;

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
