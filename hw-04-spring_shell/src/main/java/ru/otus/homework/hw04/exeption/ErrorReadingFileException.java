package ru.otus.homework.hw04.exeption;

public class ErrorReadingFileException extends RuntimeException {

    public ErrorReadingFileException(String message) {
        super(message);
    }
}
