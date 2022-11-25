package ru.otus.homework.hw06.exception;

public class ObjectExistsException extends RuntimeException {

    public ObjectExistsException(String message) {
        super(message);
    }
}
