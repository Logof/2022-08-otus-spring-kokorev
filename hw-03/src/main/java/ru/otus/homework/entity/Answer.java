package ru.otus.homework.entity;

import java.util.Objects;

public class Answer extends BasicEntity {

    private final String answerText;
    private final boolean correctAnswer;

    public Answer(String answerText, boolean correctAnswer) {
        this.answerText = answerText;
        this.correctAnswer = correctAnswer;
    }

    public String getAnswerText() {
        return answerText;
    }

    public String getAnswerTextUpperCase() {
        return answerText.toUpperCase();
    }

    public boolean isCorrectAnswer() {
        return correctAnswer;
    }

    @Override
    public String toString() {
        return answerText.trim();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }

        if (!(object instanceof Answer)) {
            return false;
        }
        return Objects.equals(((Answer) object).getAnswerText().toUpperCase(), answerText.toUpperCase());
    }

    @Override
    String toPrintable() {
        return toString();
    }
}