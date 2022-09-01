package ru.otus.logof.service;

import ru.otus.logof.entity.Question;
import ru.otus.logof.exception.QuestionNotFoundException;
import ru.otus.logof.utils.CsvUtils;

import java.util.ArrayList;
import java.util.List;

public class TestingSystemServiceImpl implements TestingSystemService {

    private List<Question> questionList;

    public List<Question> getQuestionList() {
        return questionList;
    }

    public TestingSystemServiceImpl(String testFileName) {
        try {
            questionList = CsvUtils.readTestData(testFileName);
        } catch (QuestionNotFoundException  e) {
            System.out.println(e.getMessage());
            questionList = new ArrayList<>();
        }

    }

    public void runTest() {
        if (questionList == null || questionList.size() == 0) {
            throw new QuestionNotFoundException();
        }


        for (Question question: questionList) {
            System.out.println(question.toString());
        }
    }
}
