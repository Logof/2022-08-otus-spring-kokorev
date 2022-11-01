package ru.otus.homework.hw03.repository;

import ru.otus.homework.hw03.entity.Question;

import java.util.List;

public interface QuestionsRepository {

    List<Question> getQuestionList();

}
