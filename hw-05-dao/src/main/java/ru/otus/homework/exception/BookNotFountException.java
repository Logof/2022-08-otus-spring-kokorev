package ru.otus.homework.exception;

public class BookNotFountException extends RuntimeException {

    public BookNotFountException(String message) {
        super(message);
    }
}
