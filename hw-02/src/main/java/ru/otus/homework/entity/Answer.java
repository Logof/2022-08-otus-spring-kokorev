package ru.otus.homework.entity;

import java.util.Objects;

public class Answer implements TestEntity {
    private final String value;
    private final boolean correctAnswer;

    public Answer(String value, boolean correctAnswer) {
        this.value = value;
        this.correctAnswer = correctAnswer;
    }

    public String getValue() {
        return value;
    }

    public boolean isCorrectAnswer() {
        return correctAnswer;
    }

    @Override
    public String toString() {
        return value.trim();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }

        if (!(object instanceof Answer)) {
            return false;
        }

        return Objects.equals(((Answer)object).getValue(), this.value);
    }
}
