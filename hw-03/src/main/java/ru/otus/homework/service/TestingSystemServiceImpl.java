package ru.otus.homework.service;

import org.springframework.stereotype.Service;
import ru.otus.homework.entity.Answer;
import ru.otus.homework.entity.Question;
import ru.otus.homework.exeption.IncorrectNumberAnswerException;
import ru.otus.homework.repository.QuestionsRepository;

import java.util.List;

@Service
public class TestingSystemServiceImpl implements TestingSystemService {

    private final IOService ioService;

    private final QuestionsRepository questionsRepository;

    private final UserService userService;

    private final QuestionService questionService;

    private final MessageService messageService;

    public TestingSystemServiceImpl(QuestionsRepository questionsRepository, IOService ioService,
                                    UserService userService, QuestionService questionService,
                                    MessageService messageService) {
        this.questionsRepository = questionsRepository;
        this.ioService = ioService;
        this.userService = userService;
        this.questionService = questionService;
        this.messageService = messageService;
    }

    @Override
    public void runTesting() {
        userService.registerUser();

        int correctAnswers = 0;

        for (Question question : questionsRepository.getQuestionList()) {
            ioService.printQuestion(question);

            String userAnswer = null;
            switch (question.getQuestionType()) {
                case CHOICE_ANSWERS:
                    userAnswer =
                            ioService.readStringWithPrompt(messageService.getMessage("answer.choice.promt"));
                    break;
                case ENTER_ANSWER:
                    userAnswer =
                            ioService.readStringWithPrompt(messageService.getMessage("answer.enter.promt"));
                    break;
            }

            try {
                List<Answer> userAnswerList = questionService.getAnswersByUserAnswers(question, userAnswer);
                correctAnswers += questionService.checkingUserAnswers(question, userAnswerList) ? 1 : 0;
            } catch (IncorrectNumberAnswerException e) {
                ioService.messageOutput(e.getMessage());
            }
        }
        ioService.messageOutput("\n");
        ioService.messageOutput(messageService.getMessage("test.answer.true.total") + correctAnswers);
    }

}
