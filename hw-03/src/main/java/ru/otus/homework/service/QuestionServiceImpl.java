package ru.otus.homework.service;

import org.springframework.stereotype.Service;
import ru.otus.homework.entity.Answer;
import ru.otus.homework.entity.Question;
import ru.otus.homework.enums.QuestionTypeEnum;
import ru.otus.homework.exeption.IncorrectNumberAnswerException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final MessageService messageService;

    public QuestionServiceImpl(MessageService messageService) {
        this.messageService = messageService;
    }

    public List<Answer> getAnswersByUserAnswers(Question question, String userAnswerString) {
        List<Answer> result = new ArrayList<>();
        if (question == null || userAnswerString == null || userAnswerString.isEmpty()) {
            return result;
        }

        String[] rawAnswerArray = userAnswerString.split(",");

        if (question.getQuestionType() == QuestionTypeEnum.CHOICE_ANSWERS) {
            for (String answer : rawAnswerArray) {
                int answerNumber;
                try {
                    answerNumber = Integer.parseInt(answer.trim());
                } catch (NumberFormatException e) {
                    throw new IncorrectNumberAnswerException(messageService.getMessage("exception.answer.format"));
                }

                if (answerNumber >= 1 && answerNumber <= question.getAnswerOptions().size()) {
                    result.add(question.getAnswerOptions().get(answerNumber - 1));
                } else {
                    throw new IncorrectNumberAnswerException(messageService.getMessage("exception.answer.number"));
                }
            }
        } else if (question.getQuestionType() == QuestionTypeEnum.ENTER_ANSWER) {
            result.add(new Answer(rawAnswerArray[0], true));
        }

        return result;
    }

    @Override
    public boolean checkingUserAnswers(Question question, List<Answer> userAnswers) {
        if (question.getAnswerOptions() == null || question.getAnswerOptions().size() == 0) {
            return false;
        }

        if (userAnswers == null || userAnswers.size() == 0) {
            return false;
        }

        return Objects.equals(question.getCorrectAnswer().stream()
                        .collect(groupingBy(answer -> answer.getAnswerText(), counting())),
                userAnswers.stream()
                        .collect(groupingBy(answer -> answer.getAnswerText(), counting())));
    }

}
