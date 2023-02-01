package ru.otus.collectorio.exception;

public class BaseException extends Exception{
    public BaseException(String code) {
        super("ERROR --> "+code);
    }
}
