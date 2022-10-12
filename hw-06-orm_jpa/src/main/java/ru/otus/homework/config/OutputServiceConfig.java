package ru.otus.homework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.homework.service.OutputService;
import ru.otus.homework.service.impl.OutputServiceStreams;

@Configuration
public class OutputServiceConfig {

    @Bean
    public OutputService outputService() {
        return new OutputServiceStreams(System.out);
    }
}