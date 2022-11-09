package ru.otus.homework.hw02.service;

import org.springframework.stereotype.Service;
import ru.otus.homework.hw02.entity.Answer;
import ru.otus.homework.hw02.entity.Question;
import ru.otus.homework.hw02.enums.QuestionTypeEnum;
import ru.otus.homework.hw02.exeption.IncorrectNumberAnswerException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@Service
public class QuestionServiceImpl implements QuestionService {

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
                    throw new IncorrectNumberAnswerException(e.toString());
                }

                if (answerNumber >= 1 && answerNumber <= question.getAnswerOptions().size()) {
                    result.add(question.getAnswerOptions().get(answerNumber - 1));
                } else {
                    throw new IncorrectNumberAnswerException("Incorrect answer number");
                }
            }
        } else if (question.getQuestionType() == QuestionTypeEnum.ENTER_ANSWER) {
            result.add(new Answer(rawAnswerArray[0], true));
        }

        return result;
    }

    @Override
    public boolean checkingUserAnswers(Question question, List<Answer> userAnswers) {
        if (question.getAnswerOptions() == null) {
            return false;
        }

        if (userAnswers == null) {
            return false;
        }

        if (question.getAnswerOptions().size() == 0 && userAnswers.size() == 0) {
            return true;
        }

        var correctAnswerGroup = question.getCorrectAnswer().stream()
                .collect(groupingBy(answer -> answer.getAnswerText(), counting()));

        var userAnswersGroup = userAnswers.stream()
                .collect(groupingBy(answer -> answer.getAnswerText(), counting()));
        boolean res = Objects.equals(correctAnswerGroup, userAnswersGroup);
        return res;

    }


}
