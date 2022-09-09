package ru.otus.homework.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestionTests {
    @DisplayName("корректно создаётся конструктором Question")
    @Test
    void shouldHaveCorrectQuestionConstructor() {
        Question questionWithoutAnswer = new Question("Вопрос", new ArrayList<>());
        assertEquals(questionWithoutAnswer.toString(), "Вопрос:\n\n");

        List<Answer> answerList = new ArrayList<>();
        answerList.add(new Answer("Ответ", true));

        Question questionWithAnswer = new Question("Вопрос", answerList);
        assertEquals(questionWithAnswer.toString(), "Вопрос:\n\t1. Ответ\n");
    }
}
