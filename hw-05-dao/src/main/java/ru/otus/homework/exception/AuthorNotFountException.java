package ru.otus.homework.exception;

public class AuthorNotFountException extends RuntimeException {

    public AuthorNotFountException(String message) {
        super(message);
    }
}
