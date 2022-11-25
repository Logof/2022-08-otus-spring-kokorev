package ru.otus.homework.hw04.entity;

import ru.otus.homework.hw04.enums.QuestionTypeEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }

        if (!(object instanceof Question)) {
            return false;
        }

        return ((Question) object).getQuestionText().equals(questionText)
                && Objects.equals(((Question) object).getQuestionType(), this.questionType)
                && ((Question) object).getAnswerOptions().containsAll(this.answerOptions)
                && this.answerOptions.containsAll(((Question) object).getAnswerOptions());
    }

    public List<Answer> getCorrectAnswer() {
        if (questionType == QuestionTypeEnum.CHOICE_ANSWERS) {
            return answerOptions.stream().filter(Answer::isCorrectAnswer).collect(Collectors.toList());
        }
        return answerOptions;
    }

}
