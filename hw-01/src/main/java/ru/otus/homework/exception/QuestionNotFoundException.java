package ru.otus.homework.exception;

public class QuestionNotFoundException extends RuntimeException {

    private static final String ERROR_MESSAGE = "Test question not found";

    public QuestionNotFoundException() {
        super(ERROR_MESSAGE);
    }

    public QuestionNotFoundException(String additionInfo) {
        super(ERROR_MESSAGE + ": " + additionInfo);
    }
}