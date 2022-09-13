package ru.otus.homework.entity;

import java.util.Objects;

public class Answer extends BasicEntity {

    private final boolean correctAnswer;

    public Answer(String value, boolean correctAnswer) {
        super(value);
        this.correctAnswer = correctAnswer;
    }

    public boolean isCorrectAnswer() {
        return correctAnswer;
    }

    @Override
    public String toString() {
        return getValue().trim();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }

        if (!(object instanceof Answer)) {
            return false;
        }

        return Objects.equals(((Answer)object).getValue(), getValue());
    }
}
