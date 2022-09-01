package ru.otus.logof.service;

import ru.otus.logof.exception.QuestionNotFoundException;

public interface TestingSystemService {
    void runTest() throws QuestionNotFoundException;


}
