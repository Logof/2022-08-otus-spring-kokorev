package ru.otus.homework.hw04.repository;

import ru.otus.homework.hw04.entity.Question;

import java.util.List;

public interface QuestionsRepository {

    List<Question> getQuestionList();

    List<Question> getQuestionList(String filePath);
}
