package ru.otus.homework.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageSource messageSource;

    private final LocaleProvider localeProvider;


    public MessageServiceImpl(MessageSource messageSource, LocaleProvider localeProvider) {
        this.messageSource = messageSource;
        this.localeProvider = localeProvider;
    }

    @Override
    public String getMessage(String code) {
        return messageSource.getMessage(code, new Object[]{}, localeProvider.getLocale());
    }

}
