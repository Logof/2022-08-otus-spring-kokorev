package ru.otus.homework.service;

import org.springframework.stereotype.Service;
import ru.otus.homework.entity.Answer;
import ru.otus.homework.entity.Question;
import ru.otus.homework.exeption.IncorrectNumberAnswerException;
import ru.otus.homework.repository.QuestionsRepository;

import java.util.List;

@Service
public class TestingSystemServiceImpl implements TestingSystemService {
    private final static String SELECT_ONE_OR_MORE_ANSWERS = "Select one or more answers";
    private final static String ENTER_AN_ANSWER = "Enter an answer";
    private final static String COUNT_OF_CORRECT_ANSWERS = "Count of correct answers: ";

    private final IOService ioService;

    private final QuestionsRepository questionsRepository;

    private final UserService userService;

    private final QuestionService questionService;

    public TestingSystemServiceImpl(QuestionsRepository questionsRepository, IOService ioService,
                                    UserService userService, QuestionService questionService) {
        this.questionsRepository = questionsRepository;
        this.ioService = ioService;
        this.userService = userService;
        this.questionService = questionService;
    }

    @Override
    public void runTesting() {
        userService.registerUser();

        int correctAnswers = 0;

        for (Question question : questionsRepository.getQuestionList()) {
            ioService.printQuestion(question);

            String userAnswer = null;
            switch (question.getQuestionType()) {
                case CHOICE_ANSWERS: userAnswer = ioService.readStringWithPrompt(SELECT_ONE_OR_MORE_ANSWERS); break;
                case ENTER_ANSWER: userAnswer = ioService.readStringWithPrompt(ENTER_AN_ANSWER); break;
            }

            try {
                List<Answer> userAnswerList = questionService.getAnswersByUserAnswers(question, userAnswer);
                correctAnswers += questionService.checkingUserAnswers(question, userAnswerList) ? 1 : 0 ;
            } catch (IncorrectNumberAnswerException e) {
                ioService.messageOutput(e.getMessage());
            }
        }
        ioService.messageOutput("\n");
        ioService.messageOutput(COUNT_OF_CORRECT_ANSWERS + correctAnswers);
    }

}
