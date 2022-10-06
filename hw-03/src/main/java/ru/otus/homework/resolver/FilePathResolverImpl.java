package ru.otus.homework.resolver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.homework.service.LocaleProviderService;

@Component
public class FilePathResolverImpl implements FilePathResolver {

    private final String filePath;

    public FilePathResolverImpl(@Value("${application.file.location}") String fileLocation,
                                LocaleProviderService localeProviderService) {
        this.filePath = fileLocation.replaceFirst("\\$(\\w+)", localeProviderService.getLocale().getLanguage());
    }


    @Override
    public String getFilePath() {
        return filePath;
    }
}
