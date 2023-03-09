package ru.otus.collectorio.exception;

public class BaseException extends RuntimeException {
    public BaseException(String code) {
        super("ERROR --> "+code);
    }
}
