package ru.otus.homework.entity;

import ru.otus.homework.enums.QuestionTypeEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Question extends BasicEntity {

    private final List<Answer> answerOptions;

    private final QuestionTypeEnum questionType;

    public Question(String value, QuestionTypeEnum questionType, List<Answer> answerOptions) {
        super(value);
        this.questionType = questionType;
        this.answerOptions = answerOptions == null ? new ArrayList<>() : answerOptions;
    }

    public QuestionTypeEnum getQuestionType() {
        return questionType;
    }

    public List<Answer> getAnswerOptions() {
        return answerOptions;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (answerOptions.size() > 0) {
            for (Answer answer : answerOptions) {
                stringBuilder.append("\t").append(answerOptions.indexOf(answer) + 1).append(". ")
                        .append(answer.getValue()).append("\n");
            }
        }
        return getValue().trim() + ":\n" + ((answerOptions.size() == 0) ? "\n": stringBuilder.toString());
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }

        if (!(object instanceof Question)) {
            return false;
        }

        return ((Question)object).getValue().equals(getValue())
                && Objects.equals(((Question)object).getAnswerOptions(), this.answerOptions)
                && Objects.equals(((Question)object).getQuestionType(), this.questionType);
    }
}
