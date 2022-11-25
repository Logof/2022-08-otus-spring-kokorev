package ru.otus.homework.hw04.exeption;

public class IncorrectNumberAnswerException extends RuntimeException {
    public IncorrectNumberAnswerException(String message) {
        super(message);
    }
}
