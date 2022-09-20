package ru.otus.homework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.homework.service.IOService;
import ru.otus.homework.service.IOServiceImpl;

@Configuration
public class IOServiceConfig {

    @Bean
    public IOService ioService()
    {
        return new IOServiceImpl(System.out, System.in);
    }

}
