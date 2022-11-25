package ru.otus.homework.hw03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.homework.hw03.service.TestingSystemService;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args).getBean(TestingSystemService.class).runTesting();
    }
}
