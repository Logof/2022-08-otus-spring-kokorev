package ru.otus.homework.hw04.service;

import ru.otus.homework.hw04.entity.User;
import ru.otus.homework.hw04.exeption.UserNotRegisteredException;

public interface TestingSystemService {
    void runTesting(User user) throws UserNotRegisteredException;

    void runTesting();
}
