package ru.otus.homework.service;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class IOServiceImpl implements IOService {

    private final PrintStream output;

    private final Scanner input;

    public IOServiceImpl(OutputStream outputStream, InputStream inputStream) {
        this.output = new PrintStream(outputStream);
        this.input = new Scanner(inputStream);
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
