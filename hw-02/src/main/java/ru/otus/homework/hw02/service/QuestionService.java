package ru.otus.homework.hw02.service;

import ru.otus.homework.hw02.entity.Answer;
import ru.otus.homework.hw02.entity.Question;

import java.util.List;

public interface QuestionService {
    List<Answer> getAnswersByUserAnswers(Question question, String userAnswerString);

    boolean checkingUserAnswers(Question question, List<Answer> userAnswerList);
}
