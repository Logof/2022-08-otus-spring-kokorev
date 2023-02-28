package ru.otus.collectorio.payload.response;

public enum AnswerCode {
    SUCCESS_CODE(0),
    ERROR_CODE(500),
    VERIFICATION_ERROR_CODE(400),
    NOT_ALLOWED_CODE(401),
    FORBIDDEN_CODE(403),
    NOT_FOUND_CODE(404),
    METHOD_ERROR_CODE(405),
    TOKEN_EXPIRE_CODE(4001),
    WARNING_CODE(600);


    private final int value;

    AnswerCode(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
}
