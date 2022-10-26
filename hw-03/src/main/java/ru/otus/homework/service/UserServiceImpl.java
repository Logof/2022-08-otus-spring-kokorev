package ru.otus.homework.service;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final IOService ioService;

    private final MessageService messageService;

    public UserServiceImpl(IOService ioService, MessageService messageService) {
        this.ioService = ioService;
        this.messageService = messageService;
    }

    @Override
    public void registerUser() {
        ioService.readStringWithPrompt(messageService.getMessage("user.promt.name"));
        ioService.readStringWithPrompt(messageService.getMessage("user.promt.surname"));
    }
}
