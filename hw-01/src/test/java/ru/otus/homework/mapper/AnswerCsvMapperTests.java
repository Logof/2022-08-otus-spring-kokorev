package ru.otus.homework.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.homework.hw01.entity.Answer;
import ru.otus.homework.hw01.mapper.AnswerCsvMapper;
import ru.otus.homework.hw01.mapper.CsvMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnswerCsvMapperTests {
    @DisplayName("корректно создаётся объект Answer с помощью AnswerCsvMapper")
    @Test
    public void shouldHaveCorrectAnswerCsvMapperConverter() {
        String[] rawData = {"Answer", "true"};
        CsvMapper<Answer> answerCsvMapper = new AnswerCsvMapper();
        Answer answerExpected = answerCsvMapper.toEntity(rawData);

        Answer answerActual = new Answer("Answer", true);
        assertEquals(answerExpected, answerActual);

    }
}
