package ru.otus.homework.service;

public interface IOService {
    void messageOutput(String message);

    String readStringWithPrompt(String prompt);
}
