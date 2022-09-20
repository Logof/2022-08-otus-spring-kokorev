package ru.otus.homework.service;

import ru.otus.homework.entity.Answer;
import ru.otus.homework.entity.Question;

import java.util.List;

public interface QuestionService {
    List<Answer> getAnswersByUserAnswers(Question question, String userAnswerString);

    boolean checkingUserAnswers(Question question, List<Answer> userAnswerList);
}
