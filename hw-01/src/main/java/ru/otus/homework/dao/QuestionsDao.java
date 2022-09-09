package ru.otus.homework.dao;

import ru.otus.homework.entity.Question;

import java.util.List;

public interface QuestionsDao {

    List<Question> getQuestionList();
}
