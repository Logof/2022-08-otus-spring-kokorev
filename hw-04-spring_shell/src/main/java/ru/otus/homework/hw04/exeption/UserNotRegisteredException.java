package ru.otus.homework.hw04.exeption;

public class UserNotRegisteredException extends RuntimeException {

    public UserNotRegisteredException(String message) {
        super(message);
    }
}
