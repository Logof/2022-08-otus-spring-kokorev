package ru.otus.homework.hw03.resolver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.homework.hw03.service.LocaleProvider;

@Component
public class FilePathResolverImpl implements FilePathResolver {

    private final String filePath;

    public FilePathResolverImpl(@Value("${application.file.location}") String fileLocation,
                                LocaleProvider localeProvider) {
        this.filePath = fileLocation.replaceFirst("\\$(\\w+)", localeProvider.getLocale().getLanguage());
    }


    @Override
    public String getFilePath() {
        return filePath;
    }
}
