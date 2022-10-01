package ru.otus.homework.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.homework.entity.Answer;
import ru.otus.homework.entity.Question;
import ru.otus.homework.enums.QuestionTypeEnum;
import ru.otus.homework.exeption.IncorrectNumberAnswerException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DisplayName("Проверка парсинга ввода ответов при выборе варианта ответа")
public class QuestionServiceImplTest {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private MessageService messageService;

    @DisplayName("1 ваниант правильного ответа")
    @Test
    public void shouldCorrectlyParsingUserAnswer() {

        List<Answer> answerList = new ArrayList<>();
        answerList.add(new Answer("Answer1", true));
        answerList.add(new Answer("Answer2", false));
        answerList.add(new Answer("Answer3", false));
        answerList.add(new Answer("Answer4", false));
        answerList.add(new Answer("Answer5", false));
        Question questionActual = new Question("Quest", QuestionTypeEnum.CHOICE_ANSWERS, answerList);

        List<Answer> answersExpected = questionService.getAnswersByUserAnswers(questionActual, "1");

        List<Answer> answersActual = new ArrayList<>();
        answersActual.add(new Answer("Answer1", true));

        assertEquals(answersExpected, answersActual);
    }

    @DisplayName("2 ванианта правильного ответа")
    @Test
    public void shouldCorrectlyParsingTwoUserAnswerSort() {
        List<Answer> answerList = new ArrayList<>();
        answerList.add(new Answer("Answer1", true));
        answerList.add(new Answer("Answer2", false));
        answerList.add(new Answer("Answer3", false));
        answerList.add(new Answer("Answer4", true));
        answerList.add(new Answer("Answer5", false));
        Question questionActual = new Question("Quest", QuestionTypeEnum.CHOICE_ANSWERS, answerList);

        List<Answer> answersExpected = questionService.getAnswersByUserAnswers(questionActual, "1,4");
        List<Answer> answersActual = new ArrayList<>();
        answersActual.add(new Answer("Answer1", true));
        answersActual.add(new Answer("Answer4", true));

        assertEquals(answersExpected, answersActual);
    }

    @DisplayName("Введеный ответ не попадает в список номеров вариантов ответов")
    @Test()
    public void shouldCorrectlyParsingUserAnswerNotSupport() {
        Question questionActual = new Question("Quest", QuestionTypeEnum.CHOICE_ANSWERS, new ArrayList<>());

        IncorrectNumberAnswerException thrown = Assertions.assertThrows(IncorrectNumberAnswerException.class, () -> {
            List<Answer> answersExpected = questionService.getAnswersByUserAnswers(questionActual, "100");
        });
        Assertions.assertEquals(thrown.getMessage(), messageService.getMessage("exception.answer.number"));
    }


    @DisplayName("Введеный ответ не попадает в список номеров вариантов ответов - не верный тип")
    @Test()
    public void shouldCorrectlyParsingUserAnswerNotNumber() throws IncorrectNumberAnswerException {
        Question questionActual = new Question("Quest", QuestionTypeEnum.CHOICE_ANSWERS, new ArrayList<>());

        IncorrectNumberAnswerException thrown = Assertions.assertThrows(IncorrectNumberAnswerException.class, () -> {
            List<Answer> answersExpected = questionService.getAnswersByUserAnswers(questionActual, "йцукен");
        });
        Assertions.assertEquals(thrown.getMessage(), messageService.getMessage("exception.answer.format"));
    }

    @DisplayName("Ответ не введен")
    @Test()
    public void shouldCorrectlyParsingUserAnswerNotInput() throws IncorrectNumberAnswerException {
        Question questionActual = new Question("Quest", QuestionTypeEnum.CHOICE_ANSWERS, new ArrayList<>());
        List<Answer> answersExpected = questionService.getAnswersByUserAnswers(questionActual, null);
        assertEquals(answersExpected, null);
    }

    @DisplayName("Ваниант ответа вводится пользователем")
    @Test
    public void shouldCorrectlyParsingStringUserAnswer() {
        List<Answer> answerList = new ArrayList<>();
        answerList.add(new Answer("Answer1", true));
        Question questionActual = new Question("Quest", QuestionTypeEnum.ENTER_ANSWER, answerList);

        List<Answer> answersExpected = questionService.getAnswersByUserAnswers(questionActual, "Answer1");
        List<Answer> answersActual = answersExpected.stream().filter(Answer::isCorrectAnswer).collect(Collectors.toList());

        assertEquals(answersExpected, answersActual);
    }

    /******************************************************/

    @DisplayName("Сравниваем введенный ответ пользователя с правильным ответом на вопрос")
    @Test
    public void shouldCorrectlyComparisonInputAnswer() {
        List<Answer> answerList = new ArrayList<>();
        answerList.add(new Answer("Answer1", true));
        Question questionActual = new Question("Quest", QuestionTypeEnum.ENTER_ANSWER, answerList);

        List<Answer> userAnswer = new ArrayList<>();
        userAnswer.add(new Answer("Answer1", true));

        boolean resultExpected = questionService.checkingUserAnswers(questionActual, userAnswer);
        assertEquals(resultExpected, true);
    }


    @DisplayName("Сравниваем введенный ответ пользователя с правильным ответом на вопрос")
    @Test
    public void shouldCorrectlyComparisonAnswersTrue() {
        List<Answer> answerList = new ArrayList<>();
        answerList.add(new Answer("Answer1", true));
        answerList.add(new Answer("Answer2", false));
        answerList.add(new Answer("Answer3", false));
        answerList.add(new Answer("Answer4", true));
        Question questionActual = new Question("Quest", QuestionTypeEnum.CHOICE_ANSWERS, answerList);

        List<Answer> userAnswer = new ArrayList<>();
        userAnswer.add(new Answer("Answer4", true));
        userAnswer.add(new Answer("Answer1", true));

        boolean resultExpected = questionService.checkingUserAnswers(questionActual, userAnswer);
        assertEquals(resultExpected, true);
    }

    @DisplayName("Сравниваем введенный ответ пользователя с правильным ответом на вопрос")
    @Test
    public void shouldCorrectlyComparisonAnswersFalse() {
        List<Answer> answerList = new ArrayList<>();
        answerList.add(new Answer("Answer1", true));
        answerList.add(new Answer("Answer2", false));
        answerList.add(new Answer("Answer3", false));
        answerList.add(new Answer("Answer4", true));
        Question questionActual = new Question("Quest", QuestionTypeEnum.CHOICE_ANSWERS, answerList);

        List<Answer> userAnswer = new ArrayList<>();
        userAnswer.add(new Answer("Answer4", true));

        boolean resultExpected = questionService.checkingUserAnswers(questionActual, userAnswer);
        assertEquals(resultExpected, false);
    }
}
