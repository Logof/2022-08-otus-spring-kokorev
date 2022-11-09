package ru.otus.homework.hw03.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class LocaleProviderImpl implements LocaleProvider {

    private final Locale locale;

    public LocaleProviderImpl(@Value("${application.locale}") String localeName) {
        if (localeName == null || localeName.isBlank()) {
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
