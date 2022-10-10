package ru.otus.homework.service;

import org.springframework.stereotype.Service;
import ru.otus.homework.entity.Answer;
import ru.otus.homework.entity.Question;
import ru.otus.homework.exeption.IncorrectNumberAnswerException;
import ru.otus.homework.repository.QuestionsRepository;

import java.util.List;

@Service
public class TestingSystemServiceImpl implements TestingSystemService {
    private final static String SELECT_ONE_OR_MORE_ANSWERS_CODE = "answer.enter.promt";
    private final static String ENTER_AN_ANSWER_CODE = "answer.choice.promt";
    private final static String COUNT_OF_CORRECT_ANSWERS_CODE = "test.answer.true.total";

    private final IOService ioService;

    private final QuestionsRepository questionsRepository;

    private final UserService userService;

    private final QuestionService questionService;

    private final ConverterService<Question> questionConverterService;

    private final MessageService messageService;

    public TestingSystemServiceImpl(QuestionsRepository questionsRepository, IOService ioService,
                                    UserService userService, QuestionService questionService,
                                    ConverterService<Question> questionConverterService,
                                    MessageService messageService) {
        this.questionsRepository = questionsRepository;
        this.ioService = ioService;
        this.userService = userService;
        this.questionService = questionService;
        this.questionConverterService = questionConverterService;
        this.messageService = messageService;
    }

    @Override
    public void runTesting() {
        userService.registerUser();

        int correctAnswers = 0;

        for (Question question : questionsRepository.getQuestionList()) {
            ioService.messageOutputLine(questionConverterService.toPrintable(question));

            String userAnswer = null;
            switch (question.getQuestionType()) {
                case CHOICE_ANSWERS:
                    userAnswer = ioService.readStringWithPrompt(messageService.getMessage(ENTER_AN_ANSWER_CODE));
                    break;
                case ENTER_ANSWER:
                    userAnswer = ioService.readStringWithPrompt(messageService.getMessage(SELECT_ONE_OR_MORE_ANSWERS_CODE));
                    break;
            }

            try {
                List<Answer> userAnswerList = questionService.getAnswersByUserAnswers(question, userAnswer);
                correctAnswers += questionService.checkingUserAnswers(question, userAnswerList) ? 1 : 0;
            } catch (IncorrectNumberAnswerException e) {
                ioService.messageOutputLine(e.getMessage());
            }
        }
        ioService.messageOutputLine("\n");
        ioService.messageOutputLine(messageService.getMessage(COUNT_OF_CORRECT_ANSWERS_CODE) + correctAnswers);
    }

}
