package ru.otus.homework.dao;

import ru.otus.homework.entity.Question;

import java.io.IOException;
import java.util.List;

public interface TestInstance {

    List<Question> getQuestionList() throws IOException;
}
