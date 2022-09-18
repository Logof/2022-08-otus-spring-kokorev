package ru.otus.homework.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.homework.entity.Answer;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DisplayName("Маппинг ответов ")
public class AnswerCsvMapperTest {

    private final String[] csvDataForTrueAnswer = {"Answer 1", "True"};
    private final String[] csvDataForFalseAnswer = {"Answer 2", ""};
    private final String[] csvDataForEmptyAnswer = {"Answer 3"};

    @DisplayName("корректно создаётся объект Answer с помощью AnswerCsvMapper")
    @Test
    public void shouldHaveCorrectAnswerCsvMapperConverter() {
        CsvMapper<Answer> answerCsvMapper = new AnswerCsvMapper();
        Answer trueAnswerExpected = answerCsvMapper.toEntity(csvDataForTrueAnswer);
        Answer trueAnswerActual = new Answer("Answer 1", true);
        assertEquals(trueAnswerExpected, trueAnswerActual);

        Answer falseAnswerExpected = answerCsvMapper.toEntity(csvDataForFalseAnswer);
        Answer falseAnswerActual = new Answer("Answer 2", false);
        assertEquals(falseAnswerExpected, falseAnswerActual);

        Answer emptyAnswerExpected = answerCsvMapper.toEntity(csvDataForEmptyAnswer);
        Answer emptyAnswerActual = new Answer("Answer 3", false);
        assertEquals(emptyAnswerExpected, emptyAnswerActual);
    }
}
