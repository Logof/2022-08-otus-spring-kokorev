package ru.otus.homework.hw01.dao;

import ru.otus.homework.hw01.entity.Question;

import java.util.List;

public interface QuestionsDao {

    List<Question> getQuestionList();
}
