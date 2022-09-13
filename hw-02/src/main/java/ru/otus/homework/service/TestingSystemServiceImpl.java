package ru.otus.homework.service;

import org.springframework.stereotype.Service;
import ru.otus.homework.entity.Answer;
import ru.otus.homework.entity.Question;
import ru.otus.homework.enums.QuestionTypeEnum;
import ru.otus.homework.exeption.IncorrectNumberAnswerException;
import ru.otus.homework.repository.QuestionsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@Service
public class TestingSystemServiceImpl implements TestingSystemService {
    private final static String SELECT_ONE_OR_MORE_ANSWERS = "Select one or more answers";
    private final static String ENTER_AN_ANSWER = "Enter an answer";
    private final static String COUNT_OF_CORRECT_ANSWERS = "Count of correct answers: ";

    private final IOService IOService;

    private final QuestionsRepository questionsRepository;


    public TestingSystemServiceImpl(QuestionsRepository questionsRepository, IOService IOService) {
        this.questionsRepository = questionsRepository;
        this.IOService = IOService;
    }

    @Override
    public void runTesting() {
        registration();
        int correctAnswers = 0;

        for (Question question : questionsRepository.getQuestionList()) {
            printQuestion(question);

            String userAnswer = null;
            switch (question.getQuestionType()) {
                case CHOICE_ANSWERS: userAnswer = IOService.readStringWithPrompt(SELECT_ONE_OR_MORE_ANSWERS); break;
                case ENTER_ANSWER: userAnswer = IOService.readStringWithPrompt(ENTER_AN_ANSWER); break;
            }

            try {
                List<Answer> userAnswerList = parseUserAnswer(question, userAnswer);
                correctAnswers += checkingUserAnswers(question, userAnswerList) ? 1 : 0 ;
            } catch (IncorrectNumberAnswerException e) {
                IOService.messageOutput(e.getMessage());
            }
            IOService.messageOutput("");
        }
        IOService.messageOutput("\n");
        IOService.messageOutput(COUNT_OF_CORRECT_ANSWERS + correctAnswers);
    }

    private void registration(){
        IOService.readStringWithPrompt("Enter name:");
        IOService.readStringWithPrompt("Enter surname:");
    }

    private void printQuestion(Question question) {
        IOService.messageOutput(question.getValue());
        if (question.getQuestionType() == QuestionTypeEnum.CHOICE_ANSWERS) {
            for (int i = 0; i < question.getAnswerOptions().size(); i++) {
                IOService.messageOutput("\t" + (i+1) + ". "+ question.getAnswerOptions().get(i).getValue());
            }
        }
    }

    private List<Answer> parseUserAnswer(Question question, String userAnswer) throws IncorrectNumberAnswerException {
        List<Answer> result = new ArrayList<>();

        if (userAnswer.isEmpty()) {
            return result;
        }

        if (question.getQuestionType() == QuestionTypeEnum.CHOICE_ANSWERS) {
            String[] tempAnswerArray = userAnswer.split(",");

            if (tempAnswerArray.length == 0) {
                throw new IncorrectNumberAnswerException("No answer selected");
            } else {
                for(String answer : tempAnswerArray) {
                    int answerNumber;
                    try {
                        answerNumber = Integer.parseInt(answer.trim());
                    } catch (NumberFormatException e) {
                        throw new IncorrectNumberAnswerException(e.toString());
                    }

                    if (answerNumber >= 1 && answerNumber <= question.getAnswerOptions().size()) {
                        result.add(question.getAnswerOptions().get(answerNumber - 1));
                    } else {
                        throw new IncorrectNumberAnswerException("Incorrect answer number");
                    }
                }
            }
        }

        if (question.getQuestionType() == QuestionTypeEnum.ENTER_ANSWER) {
            result.add(new Answer(userAnswer, true));
        }

        return result;
    }

    private boolean checkingUserAnswers(Question question, List<Answer> userAnswers) {
        if (question.getAnswerOptions() == null || question.getAnswerOptions().size() == 0) {
            return false;
        }

        if (userAnswers == null || userAnswers.size() == 0) {
            return false;
        }

        if (question.getQuestionType() == QuestionTypeEnum.CHOICE_ANSWERS) {
            return question.getAnswerOptions().stream().filter(Answer::isCorrectAnswer).collect(Collectors.toList())
                    .stream().collect(groupingBy(answer -> answer, counting()))
                    .equals(userAnswers.stream().collect(groupingBy(answer -> answer, counting())));
        }

        return question.getAnswerOptions().stream()
                .collect(groupingBy(answer -> answer.getValue().toUpperCase(Locale.ROOT), counting()))
                .equals(userAnswers.stream().
                        collect(groupingBy(answer -> answer.getValue().toUpperCase(Locale.ROOT), counting())));
    }

}
