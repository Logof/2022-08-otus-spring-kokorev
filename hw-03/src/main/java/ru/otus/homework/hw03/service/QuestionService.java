package ru.otus.homework.hw03.service;

import ru.otus.homework.hw03.entity.Answer;
import ru.otus.homework.hw03.entity.Question;
import ru.otus.homework.hw03.exeption.IncorrectNumberAnswerException;

import java.util.List;

public interface QuestionService {
    List<Answer> getAnswersByUserAnswers(Question question, String userAnswerString) throws IncorrectNumberAnswerException;

    boolean checkingUserAnswers(Question question, List<Answer> userAnswerList);
}
