package ru.otus.homework.service;

import ru.otus.homework.exception.QuestionNotFoundException;

public interface TestingSystemService {
    void runTest() throws QuestionNotFoundException;
}
