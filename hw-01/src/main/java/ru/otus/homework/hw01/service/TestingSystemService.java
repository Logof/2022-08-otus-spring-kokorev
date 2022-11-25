package ru.otus.homework.hw01.service;

import ru.otus.homework.hw01.exception.QuestionNotFoundException;

public interface TestingSystemService {
    void startTesting() throws QuestionNotFoundException;
}
