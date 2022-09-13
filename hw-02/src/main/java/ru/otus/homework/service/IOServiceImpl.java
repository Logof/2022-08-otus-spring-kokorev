package ru.otus.homework.service;

import org.springframework.stereotype.Service;

import java.io.PrintStream;
import java.util.Scanner;

@Service
public class IOServiceImpl implements IOService {

    private final PrintStream output;

    private final Scanner input;

    public IOServiceImpl() {
        this.output = new PrintStream(System.out);
        this.input = new Scanner(System.in);
    }

    @Override
    public void messageOutput(String message) {
        output.println(message);
    }

    @Override
    public String readStringWithPrompt(String prompt) {
        output.println(prompt);
        return input.nextLine();
    }
}
