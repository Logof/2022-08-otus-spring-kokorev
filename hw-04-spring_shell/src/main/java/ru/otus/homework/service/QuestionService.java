package ru.otus.homework.service;

import ru.otus.homework.entity.Answer;
import ru.otus.homework.entity.Question;
import ru.otus.homework.exeption.IncorrectNumberAnswerException;

import java.util.List;

public interface QuestionService {
    List<Answer> getAnswersByUserAnswers(Question question, String userAnswerString) throws IncorrectNumberAnswerException;

    boolean checkingUserAnswers(Question question, List<Answer> userAnswerList);
}
