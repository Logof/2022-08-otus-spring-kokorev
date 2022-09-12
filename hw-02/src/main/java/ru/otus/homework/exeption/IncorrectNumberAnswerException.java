package ru.otus.homework.exeption;

public class IncorrectNumberAnswerException extends RuntimeException {
    public IncorrectNumberAnswerException(String message) {
        super(message);
    }
}
