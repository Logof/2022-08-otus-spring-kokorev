package ru.otus.homework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;

@Configuration
public class IOServiceConfigure {
    @Bean
    public Scanner scanner() {
        return new Scanner(new InputStreamReader(System.in));
    }

    @Bean
    public PrintStream printStream() {
        return System.out;
    }
}
