package ru.otus.homework.hw03.exeption;

public class ErrorReadingFileException extends RuntimeException {

    public ErrorReadingFileException(String message) {
        super(message);
    }
}
