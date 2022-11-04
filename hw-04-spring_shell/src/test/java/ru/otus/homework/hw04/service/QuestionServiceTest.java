package ru.otus.homework.hw04.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.homework.hw04.entity.Answer;
import ru.otus.homework.hw04.entity.Question;
import ru.otus.homework.hw04.enums.QuestionTypeEnum;
import ru.otus.homework.hw04.exeption.IncorrectNumberAnswerException;
import ru.otus.homework.hw04.repository.QuestionsRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
//TODO поправить тесты, чтобы они работали
public class QuestionServiceTest {

    @MockBean
    private QuestionsRepository questionsRepository;

    @MockBean
    private QuestionService questionService;

    @MockBean
    private MessageService messageService;

    private List<Question> getQuestionsForTest1() {
        List<Question> questionList = new ArrayList<>();
        questionList.add(new Question("Question", QuestionTypeEnum.ENTER_ANSWER,
                Collections.singletonList(new Answer("hello world", true))));

        List<Answer> answerList1 = new ArrayList<>();
        answerList1.add(new Answer("True answer", true));
        answerList1.add(new Answer("True false", false));
        answerList1.add(new Answer("True answer", true));
        questionList.add(new Question("Question", QuestionTypeEnum.CHOICE_ANSWERS, answerList1));

        List<Answer> answerList2 = new ArrayList<>();
        answerList2.add(new Answer("True answer", true));
        answerList2.add(new Answer("True false", false));
        answerList2.add(new Answer("True false", false));

        questionList.add(new Question("Question", QuestionTypeEnum.CHOICE_ANSWERS, answerList2));
        return questionList;
    }

    @Test
    @DisplayName("Вараант 1: Три ответа верные")
    public void test1() {

        String[] answersArray = {"hello world", "1,3", "1"};

        when(questionsRepository.getQuestionList()).thenReturn(getQuestionsForTest1());
        List<Question> questions = questionsRepository.getQuestionList();

        int i = 0;
        for (Question question : questions) {
            List<Answer> userAnswer = questionService.getAnswersByUserAnswers(question, answersArray[i]);
            assertTrue(questionService.checkingUserAnswers(question, userAnswer));
            i += 1;
        }

    }

    @Test
    @DisplayName("Вараант 2: Три ответа верные. Ответ из нескольких верных вариантов - инвертирован")
    public void test2() {
        String[] answersArray = {"hello world", "3, 1", "1"};
        List<Question> questionList = questionsRepository.getQuestionList();

        int i = 0;
        for (Question question : questionList) {
            List<Answer> userAnswer = questionService.getAnswersByUserAnswers(question, answersArray[i]);
            assertTrue(questionService.checkingUserAnswers(question, userAnswer));
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
    @DisplayName("Вариант 4: Не верный формат ответа или ответ не входит в диапазон вариантов ответа")
    public void test4() {
        String[] answersArray = {"HELLO world", "qwerty", "100"};

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
                assertTrue(questionService.checkingUserAnswers(question, userAnswer));
            }
        }
    }
}
