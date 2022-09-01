package service;

import exception.QuestionNotFoundException;
import entity.Question;
import utils.CsvUtils;

import java.util.ArrayList;
import java.util.List;


public class TestServiceImpl implements TestService{

    private List<Question> questionList;

    public List<Question> getQuestionList() {
        return questionList;
    }

    public TestServiceImpl(String testFileName) {
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
