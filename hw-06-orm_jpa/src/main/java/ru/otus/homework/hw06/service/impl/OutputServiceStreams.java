package ru.otus.homework.hw06.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.homework.hw06.service.OutputService;

import java.io.PrintStream;

@Service
public class OutputServiceStreams implements OutputService {
    private final PrintStream output;

    public OutputServiceStreams() {
        output = new PrintStream(System.out);
    }

    @Override
    public void outString(String s) {
        output.println(s);
    }

}
