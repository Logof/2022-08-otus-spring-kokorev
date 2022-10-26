package ru.otus.homework.exeption;

public class ErrorReadingFileException extends RuntimeException {

    public ErrorReadingFileException(String message) {
        super(message);
    }
}
