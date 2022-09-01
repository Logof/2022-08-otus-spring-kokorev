package ru.otus.homework;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.homework.exception.QuestionNotFoundException;
import ru.otus.homework.service.TestingSystemService;
import ru.otus.homework.service.TestingSystemServiceImpl;

public class Application {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");

        TestingSystemService testingSystemService = context.getBean(TestingSystemServiceImpl.class);
        try {
            testingSystemService.runTest();
        } catch (QuestionNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
