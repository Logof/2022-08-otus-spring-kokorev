package ru.otus.homework.hw12.exception;

import java.util.Arrays;
import java.util.stream.Collectors;

public class FieldRequiredException extends RuntimeException {
    private final static String basicMessage = "Required field(s): %s";

    public FieldRequiredException(String... fields) {
        super(String.format(basicMessage, Arrays.stream(fields).collect(Collectors.joining(", "))));
    }

    public FieldRequiredException(String field) {
        super(String.format(basicMessage, field));
    }
}
