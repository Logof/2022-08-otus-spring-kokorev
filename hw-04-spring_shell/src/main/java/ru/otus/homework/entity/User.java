package ru.otus.homework.entity;

public class User {
    private String name;
    private String surname;

    private int countCorrectAnswers;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getCountCorrectAnswers() {
        return countCorrectAnswers;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void addCorrectAnswer() {
        this.countCorrectAnswers += 1;
    }

    public void clearCountCorrectAnswers() {
        this.countCorrectAnswers = 0;
    }
}
