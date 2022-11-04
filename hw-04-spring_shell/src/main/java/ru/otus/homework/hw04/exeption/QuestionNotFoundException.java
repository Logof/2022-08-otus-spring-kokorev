package ru.otus.homework.hw04.exeption;

public class QuestionNotFoundException extends RuntimeException {
    private static final String ERROR_MESSAGE = "Test question not found";

    public QuestionNotFoundException(String additionInfo) {
        super(ERROR_MESSAGE + ": " + additionInfo);
    }
}
