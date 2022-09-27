package ru.otus.homework.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageSource messageSource;

    private final LocaleProviderService localeProviderService;


    public MessageServiceImpl(MessageSource messageSource, LocaleProviderService localeProviderService) {
        this.messageSource = messageSource;
        this.localeProviderService = localeProviderService;
    }

    @Override
    public String getMessage(String code) {
        return messageSource.getMessage(code, new Object[]{}, localeProviderService.getLocale());
    }

}
