package ru.otus.homework.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.homework.entity.Answer;
import ru.otus.homework.entity.Question;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestionCsvMapperTests {


    @DisplayName("корректно создаётся объект Question с помощью QuestionCsvMapper")
    @Test
    public void shouldHaveCorrectQuestionCsvMapperConverter() {
        CsvMapper questionCsvMapper = new QuestionCsvMapper(new AnswerCsvMapper());

        String[] rawData = {"Вопрос", "Ответ", "true"};
        Question questionExpected = questionCsvMapper.toEntity(rawData);

        List<Answer> answerList = new ArrayList<>();
        answerList.add(new Answer("Ответ", true));
        Question questionActual = new Question("Вопрос", answerList);
        assertEquals(questionExpected, questionActual);

    }
}
