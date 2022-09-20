package ru.otus.homework.service;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final IOService ioService;

    public UserServiceImpl(IOService ioService) {
        this.ioService = ioService;
    }

    @Override
    public void registerUser(){
        ioService.readStringWithPrompt("Enter name:");
        ioService.readStringWithPrompt("Enter surname:");
    }
}
