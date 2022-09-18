package ru.otus.homework.service;

import ru.otus.homework.entity.Question;

public interface IOService {
    void messageOutput(String message);

    String readStringWithPrompt(String prompt);

    void printQuestion(Question question);
}
