package ru.otus.homework.hw01.service;

public class OutputServiceImpl implements OutputService {
    @Override
    public void messageOutput(String message) {
        System.out.println(message);
    }
}
