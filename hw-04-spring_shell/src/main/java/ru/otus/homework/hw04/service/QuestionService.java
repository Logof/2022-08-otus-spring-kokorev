package ru.otus.homework.hw04.service;

import ru.otus.homework.hw04.entity.Answer;
import ru.otus.homework.hw04.entity.Question;
import ru.otus.homework.hw04.exeption.IncorrectNumberAnswerException;

import java.util.List;

public interface QuestionService {
    List<Answer> getAnswersByUserAnswers(Question question, String userAnswerString) throws IncorrectNumberAnswerException;

    boolean checkingUserAnswers(Question question, List<Answer> userAnswerList);
}
