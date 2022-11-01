package ru.otus.homework.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.homework.hw01.entity.Answer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnswerTests {
    @DisplayName("корректно создаётся конструктором Answer")
    @Test
    void shouldHaveCorrectAnswerConstructor() {
        Answer answerTest = new Answer("Ответ", false);
        assertEquals(answerTest.getAnswer(), "Ответ");
        assertEquals(answerTest.isCorrectAnswer(), false);
    }
}
