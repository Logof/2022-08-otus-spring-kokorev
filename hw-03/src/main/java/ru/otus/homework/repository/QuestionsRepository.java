package ru.otus.homework.repository;

import ru.otus.homework.entity.Question;

import java.util.List;

public interface QuestionsRepository {

    List<Question> getQuestionList();

}
