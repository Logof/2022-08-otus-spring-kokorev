package ru.otus.collectorio.exception;

public class UserException extends BaseException {
    public UserException(String code) {
        super(code);
    }
    public static UserException notFound(){
        return new UserException("Not Found");
    }

    public static UserException userExists(){
        return new UserException("User already exists");
    }
}
