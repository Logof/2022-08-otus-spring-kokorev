package ru.otus.logof.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Question {
    private String question;

    private List<Answer> answers;

    public String getQuestion(){
        return this.question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (answers.size() != 0 ) {
            for (Answer answer: answers) {
                stringBuilder.append("\t").append(answers.indexOf(answer) + 1).append(". ")
                        .append(answer.toString()+"\n");
            }
        }

        return question.trim() + ":\n" + ((answers.size() == 0) ? "\n": stringBuilder.toString());
    }

    public Question() {
        this.question = null;
        this.answers = new ArrayList<>();
    }

    public Question(String question) {
        this.question = question;
        this.answers = new ArrayList<>();
    }

    public Question(String question, List<Answer> answerList) {
        this.question = question;
        this.answers = answerList;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }

        if (!(object instanceof Question)) {
            return false;
        }

        return ((Question)object).getQuestion().equals(this.question)
                && Objects.equals(((Question)object).getAnswers(), this.answers);
    }

}
