package ru.otus.homework.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.homework.hw01.entity.Answer;
import ru.otus.homework.hw01.entity.Question;
import ru.otus.homework.hw01.mapper.AnswerCsvMapper;
import ru.otus.homework.hw01.mapper.CsvMapper;
import ru.otus.homework.hw01.mapper.QuestionCsvMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestionCsvMapperTests {


    @DisplayName("корректно создаётся объект Question с помощью QuestionCsvMapper")
    @Test
    public void shouldHaveCorrectQuestionCsvMapperConverter() {
        CsvMapper<Question> questionCsvMapper = new QuestionCsvMapper(new AnswerCsvMapper());

        String[] rawData = {"Вопрос", "Ответ", "true"};
        Question questionExpected = questionCsvMapper.toEntity(rawData);

        List<Answer> answerList = new ArrayList<>();
        answerList.add(new Answer("Ответ", true));
        Question questionActual = new Question("Вопрос", answerList);
        assertEquals(questionExpected, questionActual);

    }
}
