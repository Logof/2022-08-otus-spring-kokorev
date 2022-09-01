package ru.otus.homework;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.homework.entity.Answer;
import ru.otus.homework.entity.Question;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EntitiesTests {
    @DisplayName("корректно создаётся конструктором Answer")
    @Test
    void shouldHaveCorrectAnswerConstructor() {
        Answer answer = new Answer("Ответ", false);
        assertEquals(answer.getAnswer(), "Ответ");
        assertEquals(answer.isCorrectAnswer(), false);
    }

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
