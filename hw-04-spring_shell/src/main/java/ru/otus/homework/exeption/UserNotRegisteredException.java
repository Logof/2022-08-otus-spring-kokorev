package ru.otus.homework.exeption;

public class UserNotRegisteredException extends RuntimeException {

    public UserNotRegisteredException(String message) {
        super(message);
    }
}
