package ru.otus.homework.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.homework.entity.Answer;
import ru.otus.homework.entity.Question;
import ru.otus.homework.exeption.IncorrectNumberAnswerException;
import ru.otus.homework.repository.QuestionsRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class QuestionServiceTest {

    @Autowired
    private QuestionsRepository questionsRepository;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private MessageService messageService;

    @Test
    @DisplayName("Вараант 1: Три ответа верные")
    public void test1() {
        String[] answersArray = {"hello world", "1,3", "1"};
        boolean[] resultActual = {true, true, true};

        List<Question> questionList = questionsRepository.getQuestionList();

        int i = 0;
        for (Question question : questionList) {
            List<Answer> userAnswer = questionService.getAnswersByUserAnswers(question, answersArray[i]);
            assertEquals(questionService.checkingUserAnswers(question, userAnswer), resultActual[i]);
            i += 1;
        }

    }

    @Test
    @DisplayName("Вараант 2: Три ответа верные. Ответ из нескольких верных вариантов - инвертирован")
    public void test2() {
        String[] answersArray = {"hello world", "3, 1", "1"};
        boolean[] resultActual = {true, true, true};

        List<Question> questionList = questionsRepository.getQuestionList();

        int i = 0;
        for (Question question : questionList) {
            List<Answer> userAnswer = questionService.getAnswersByUserAnswers(question, answersArray[i]);
            assertEquals(questionService.checkingUserAnswers(question, userAnswer), resultActual[i]);
            i += 1;
        }

    }

    @Test
    @DisplayName("Вариант 3: Один ответ с ошибкой")
    public void test3() {
        String[] answersArray = {"HELLO world", "1", "1"};
        boolean[] resultActual = {true, false, true};

        List<Question> questionList = questionsRepository.getQuestionList();

        int i = 0;
        for (Question question : questionList) {
            List<Answer> userAnswer = questionService.getAnswersByUserAnswers(question, answersArray[i]);
            assertEquals(questionService.checkingUserAnswers(question, userAnswer), resultActual[i]);
            i += 1;
        }
    }

    @Test
    @DisplayName("Вариант 4: Не верный формат ответа или ответ не входит в диапозон вариантов ответа")
    public void test4() {
        String[] answersArray = {"HELLO world", "qwerty", "100"};
        boolean[] resultActual = {true, false, false};

        List<Question> questionList = questionsRepository.getQuestionList();

        for (int i = 0; i < questionList.size(); i++) {
            String userAnswerString = answersArray[i];
            Question question = questionList.get(i);

            if (i > 1) {
                IncorrectNumberAnswerException thrown = Assertions.assertThrows(IncorrectNumberAnswerException.class, () -> {
                    List<Answer> userAnswer = questionService.getAnswersByUserAnswers(question, userAnswerString);
                });
                Assertions.assertEquals(thrown.getMessage(), messageService.getMessage("exception.answer.number"));
            } else {
                List<Answer> userAnswer = questionService.getAnswersByUserAnswers(question, userAnswerString);
                assertEquals(questionService.checkingUserAnswers(question, userAnswer), resultActual[i]);
            }
        }
    }
}
