package ru.otus.homework.hw02.exeption;

public class ErrorReadingFileException extends RuntimeException {

    public ErrorReadingFileException(String message) {
        super(message);
    }
}
