package ru.otus.homework.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.homework.hw03.entity.Answer;
import ru.otus.homework.hw03.entity.Question;
import ru.otus.homework.hw03.exeption.IncorrectNumberAnswerException;
import ru.otus.homework.hw03.repository.QuestionsRepository;
import ru.otus.homework.hw03.service.MessageService;
import ru.otus.homework.hw03.service.QuestionService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

        List<Question> questionList = questionsRepository.getQuestionList();

        int i = 0;
        for (Question question : questionList) {
            List<Answer> userAnswer = questionService.getAnswersByUserAnswers(question, answersArray[i]);
            assertTrue(questionService.checkingUserAnswers(question, userAnswer));
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

            if (i > 0) {
                IncorrectNumberAnswerException thrown = Assertions.assertThrows(IncorrectNumberAnswerException.class, () -> {
                    questionService.getAnswersByUserAnswers(question, userAnswerString);
                });
                Assertions.assertEquals(thrown.getMessage(),
                        (i == 1) ? messageService.getMessage("exception.answer.format") :
                                messageService.getMessage("exception.answer.number")
                );
            } else {
                List<Answer> userAnswer = questionService.getAnswersByUserAnswers(question, userAnswerString);
                assertEquals(questionService.checkingUserAnswers(question, userAnswer), resultActual[i]);
            }
        }
    }
}
