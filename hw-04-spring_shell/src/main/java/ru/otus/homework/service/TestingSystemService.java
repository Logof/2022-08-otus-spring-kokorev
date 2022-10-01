package ru.otus.homework.service;

import ru.otus.homework.entity.User;
import ru.otus.homework.exeption.UserNotRegisteredException;

public interface TestingSystemService {
    void runTesting(User user) throws UserNotRegisteredException;

    void runTesting();
}
