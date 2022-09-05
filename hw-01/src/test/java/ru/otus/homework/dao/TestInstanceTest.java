package ru.otus.homework.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.homework.entity.Answer;
import ru.otus.homework.entity.Question;
import ru.otus.homework.mapper.AnswerCsvMapper;
import ru.otus.homework.mapper.QuestionCsvMapper;
import ru.otus.homework.service.QuestionFileReader;
import ru.otus.homework.service.QuestionFileReaderImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestInstanceTest {

    private QuestionFileReader questionFileReader;
    private QuestionCsvMapper questionCsvMapper;

    @BeforeEach
    public void setup() {
        questionFileReader = new QuestionFileReaderImpl();
        questionCsvMapper = new QuestionCsvMapper(new AnswerCsvMapper());
    }

    @Test
    @DisplayName("Экземпляр теста правильно собирается")
    public void readTestDataTest() throws IOException {
        TestInstance testInstance = new TestInstance(questionFileReader, questionCsvMapper, "/test.csv");
        testInstance.readTestData();

        List<Question> questionListExpected = testInstance.getQuestionList();

        List<Question> questionListActual = new ArrayList<>();
        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer("Answer 1", true));
        answers.add(new Answer("Answer 2", false));
        answers.add(new Answer("Answer 3", false));

        Question question = new Question("Question 1", answers);
        questionListActual.add(question);

        assertEquals(questionListExpected, questionListActual);
    }
}
