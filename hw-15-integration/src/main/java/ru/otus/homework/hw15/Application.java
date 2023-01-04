package ru.otus.homework.hw15;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.homework.hw15.service.ButterflyService;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        ButterflyService butterflyService = context.getBean(ButterflyService.class);
        butterflyService.launchIncubator();
    }
}
