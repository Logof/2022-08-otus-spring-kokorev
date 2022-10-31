package ru.otus.homework.exception;

public class ObjectExistsException extends RuntimeException {

    public ObjectExistsException(String message) {
        super(message);
    }
}
