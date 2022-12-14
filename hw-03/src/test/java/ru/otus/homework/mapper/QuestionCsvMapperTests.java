package ru.otus.homework.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework.hw03.entity.Answer;
import ru.otus.homework.hw03.entity.Question;
import ru.otus.homework.hw03.enums.QuestionTypeEnum;
import ru.otus.homework.hw03.mapper.AnswerCsvMapper;
import ru.otus.homework.hw03.mapper.QuestionCsvMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@DisplayName("Маппинг вопросов ")
@ExtendWith(MockitoExtension.class)
public class QuestionCsvMapperTests {

    @Mock
    private AnswerCsvMapper answerCsvMapper;

    @InjectMocks
    private QuestionCsvMapper questionCsvMapper;

    @DisplayName("корректно создаётся объект Question с помощью QuestionCsvMapper")
    @Test
    public void shouldHaveCorrectQuestionCsvMapperConverter() {

        String[] rawData = {"Вопрос", "ENTER_ANSWER", "Ответ", "true"};
        given(answerCsvMapper.toEntity(Arrays.copyOfRange(rawData, 2, rawData.length)))
                .willReturn(new Answer("Ответ", true));

        Question questionExpected = questionCsvMapper.toEntity(rawData);

        List<Answer> answerList = new ArrayList<>();
        answerList.add(new Answer("Ответ", true));
        Question questionActual = new Question("Вопрос", QuestionTypeEnum.ENTER_ANSWER, answerList);

        assertEquals(questionExpected, questionActual);

    }
}
