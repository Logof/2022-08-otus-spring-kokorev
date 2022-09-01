package ru.otus.homework.entity;

import java.util.Objects;

public class Answer {
    private String answer;
    private boolean correctAnswer;

    public String getAnswer() {
        return answer;
    }

    public boolean isCorrectAnswer() {
        return correctAnswer;
    }

    public Answer(String value, boolean correctAnswer) {
        this.answer = value;
        this.correctAnswer = correctAnswer;
    }

    @Override
    public String toString() {
        return answer.trim();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }

        if (!(object instanceof Answer)) {
            return false;
        }

        return ((Answer)object).getAnswer().equals(this.answer)
                && Objects.equals(((Answer)object).isCorrectAnswer(), this.correctAnswer);
    }
}
