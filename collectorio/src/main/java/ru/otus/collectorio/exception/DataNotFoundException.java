package ru.otus.collectorio.exception;

public class DataNotFoundException extends RuntimeException {

    private final static String ERROR_MESSAGE = "Запрос не вернул данных";
    public DataNotFoundException() {
        super(ERROR_MESSAGE);
    }
}
