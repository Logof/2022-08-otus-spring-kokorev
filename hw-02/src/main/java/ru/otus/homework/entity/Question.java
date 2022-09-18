package ru.otus.homework.entity;

import ru.otus.homework.enums.QuestionTypeEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Question extends BasicEntity {

    private final String questionText;

    private final List<Answer> answerOptions;

    private final QuestionTypeEnum questionType;

    public Question(String questionText, QuestionTypeEnum questionType, List<Answer> answerOptions) {
        this.questionText = questionText;
        this.questionType = questionType;
        this.answerOptions = answerOptions == null ? new ArrayList<>() : answerOptions;
    }

    public QuestionTypeEnum getQuestionType() {
        return questionType;
    }

    public List<Answer> getAnswerOptions() {
        return answerOptions;
    }

    public String getQuestionText() {
        return questionText;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (answerOptions.size() > 0) {
            for (Answer answer : answerOptions) {
                stringBuilder.append("\t").append(answerOptions.indexOf(answer) + 1).append(". ")
                        .append(answer.getAnswerText()).append("\n");
            }
        }
        return questionText.trim() + ":\n" + ((answerOptions.size() == 0) ? "\n": stringBuilder.toString());
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }

        if (!(object instanceof Question)) {
            return false;
        }

        return ((Question)object).getQuestionText().equals(questionText)
                && Objects.equals(((Question)object).getAnswerOptions().stream().collect(groupingBy(answer -> answer, counting())),
                this.answerOptions.stream().collect(groupingBy(answer -> answer, counting())))
                && Objects.equals(((Question)object).getQuestionType(), this.questionType);
    }

    public List<Answer> getCorrectAnswer() {
        if (questionType == QuestionTypeEnum.CHOICE_ANSWERS) {
            return answerOptions.stream().filter(Answer::isCorrectAnswer).collect(Collectors.toList());
        }
        return answerOptions;
    }

}
