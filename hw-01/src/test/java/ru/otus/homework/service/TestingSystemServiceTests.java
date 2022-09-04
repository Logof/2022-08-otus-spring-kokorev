package ru.otus.homework.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestingSystemServiceTests {
    /*@DisplayName("корректно создаётся объект TestService без корректного указания файла")
    @Test
    public void shouldHaveCorrectTestServiceConstructor() {
        TestingSystemServiceImpl testServiceExpected = new TestingSystemServiceImpl("");
        //List<Question> questionListExpected =  testServiceExpected.getQuestionList();
        assertEquals(questionListExpected, new ArrayList<Question>());
    }

    @DisplayName("корректно создаётся объект TestService")
    @Test
    public void shouldHaveCorrectTestServiceConstructorWithFile() {
        TestingSystemServiceImpl testServiceExpected = new TestingSystemServiceImpl("/test.csv");
        List<Question> questionListExpected =  testServiceExpected.getQuestionList();

        List<Question> questionListActual =  testServiceExpected.getQuestionList();
        List<AnswerTests> answerTestList = new ArrayList<>();
        answerTestList.add(new AnswerTests("Answer 1", true));
        answerTestList.add(new AnswerTests("Answer 2", false));
        answerTestList.add(new AnswerTests("Answer 3", false));

        questionListActual.add(new Question("Question 1", answerTestList));
        assertEquals(questionListExpected, questionListActual);
    }*/
}
