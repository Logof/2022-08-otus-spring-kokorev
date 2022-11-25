package ru.otus.homework.hw02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import ru.otus.homework.hw02.service.TestingSystemService;

@ComponentScan
@PropertySource("classpath:application.properties")
public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Application.class);

        TestingSystemService testingSystemService = context.getBean(TestingSystemService.class);
        testingSystemService.runTesting();
    }
}
