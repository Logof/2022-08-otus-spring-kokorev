package ru.otus.homework.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class LocaleProviderServiceImpl implements LocaleProviderService {

    private final Locale locale;

    public LocaleProviderServiceImpl() {
        this.locale = Locale.getDefault();
    }

    public LocaleProviderServiceImpl(@Value("${application.locale}") String localeName) {
        if (localeName.isBlank()) {
            this.locale = Locale.getDefault();
        } else {
            this.locale = new Locale(localeName);
        }
    }

    @Override
    public Locale getLocale() {
        return locale;
    }
}
