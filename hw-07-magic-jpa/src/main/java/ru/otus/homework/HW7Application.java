package ru.otus.homework;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class HW7Application {
    public static void main(String[] args) throws SQLException {
        Console.main();
        SpringApplication.run(HW7Application.class, args);
    }
}