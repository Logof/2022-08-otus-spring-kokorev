package ru.otus.homework.hw04.resolver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class FilePathRecolverImpl implements FilePathResolver {

    private final String filePath;

    public FilePathRecolverImpl(@Value("${application.file.location}") String fileLocation,
                                @Value("${application.locale}") Locale locale) {
        this.filePath = fileLocation.replaceFirst("\\$(\\w+)", locale.getLanguage());
    }

    @Override
    public String getFilePath() {
        return filePath;
    }
}
