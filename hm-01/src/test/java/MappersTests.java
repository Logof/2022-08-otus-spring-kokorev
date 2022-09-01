import entity.Answer;
import entity.Question;
import mapper.AnswerCsvMapper;
import mapper.CsvMapper;
import mapper.QuestionCsvMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MappersTests {
    @DisplayName("корректно создаётся объект Answer с помощью AnswerCsvMapper")
    @Test
    public void shouldHaveCorrectAnswerCsvMapperConverter() {
        String[] rawData = {"Answer", "true"};
        CsvMapper answerCsvMapper = new AnswerCsvMapper();
        Answer answerExpected = answerCsvMapper.toEntity(rawData);

        Answer answerActual = new Answer("Answer", true);
        assertEquals(answerExpected, answerActual);

    }

    @DisplayName("корректно создаётся объект Question с помощью QuestionCsvMapper")
    @Test
    public void shouldHaveCorrectQuestionCsvMapperConverter() {
        CsvMapper questionCsvMapper = new QuestionCsvMapper();

        String[] rawData = {"Вопрос", "Ответ", "true"};
        Question questionExpected = questionCsvMapper.toEntity(rawData);

        List<Answer> answerList = new ArrayList<>();
        answerList.add(new Answer("Ответ", true));
        Question questionActual = new Question("Вопрос", answerList);
        assertEquals(questionExpected, questionActual);

    }

}
