package ru.otus.homework.hw02.exeption;

public class IncorrectNumberAnswerException extends RuntimeException {
    public IncorrectNumberAnswerException(String message) {
        super(message);
    }
}
