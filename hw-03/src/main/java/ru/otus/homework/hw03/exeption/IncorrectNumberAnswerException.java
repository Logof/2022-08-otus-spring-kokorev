package ru.otus.homework.hw03.exeption;

public class IncorrectNumberAnswerException extends RuntimeException {
    public IncorrectNumberAnswerException(String message) {
        super(message);
    }
}
