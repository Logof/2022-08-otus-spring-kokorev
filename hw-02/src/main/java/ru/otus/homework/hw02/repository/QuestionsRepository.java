package ru.otus.homework.hw02.repository;

import ru.otus.homework.hw02.entity.Question;

import java.util.List;

public interface QuestionsRepository {

    List<Question> getQuestionList();
}
