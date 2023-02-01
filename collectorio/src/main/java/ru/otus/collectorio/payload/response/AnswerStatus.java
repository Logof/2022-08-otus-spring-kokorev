package ru.otus.collectorio.payload.response;

public enum AnswerStatus {
    SUCCESS_STATUS("success"),
    ERROR_STATUS("error");

    private final String value;

    AnswerStatus(final String newValue) {
        value = newValue;
    }

    public String getValue() { return value; }
}
