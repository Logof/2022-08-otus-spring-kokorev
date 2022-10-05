package ru.otus.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.homework.service.OutputService;

import java.io.PrintStream;

@Service
public class OutputServiceStreams implements OutputService {
    private final PrintStream output;

    public OutputServiceStreams() {
        output = System.out;
    }

    @Override
    public void outputString(String s) {
        output.println(s);
    }

}
