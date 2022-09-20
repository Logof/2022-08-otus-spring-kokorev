package ru.otus.homework.service;

public interface IOService {
    void messageOutputLine(String message);

    String readStringWithPrompt(String prompt);
}
