package ru.otus.homework.hw04.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageSource messageSource;

    private final Locale locale;

    public MessageServiceImpl(MessageSource messageSource, @Value("${application.locale}") String localeName) {
        this.messageSource = messageSource;
        this.locale = new Locale(localeName);
    }

    @Override
    public String getMessage(String code) {
        return messageSource.getMessage(code, new Object[]{}, locale);
    }

}
