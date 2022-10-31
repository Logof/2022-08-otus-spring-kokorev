package ru.otus.homework.hw04.service;

import org.springframework.stereotype.Service;
import ru.otus.homework.hw04.entity.User;

@Service
public class UserServiceImpl implements UserService {
    private final IOService ioService;

    private final MessageService messageService;

    public UserServiceImpl(IOService ioService, MessageService messageService) {
        this.ioService = ioService;
        this.messageService = messageService;
    }

    @Override
    public User registerNewUser() {
        User newUser = new User();
        newUser.setName(ioService.readStringWithPrompt(messageService.getMessage("user.promt.name")));
        newUser.setSurname(ioService.readStringWithPrompt(messageService.getMessage("user.promt.surname")));

        return newUser;
    }
}
