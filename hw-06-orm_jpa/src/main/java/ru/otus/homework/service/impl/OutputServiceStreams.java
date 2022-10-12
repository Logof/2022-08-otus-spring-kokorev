package ru.otus.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.homework.service.OutputService;

import java.io.OutputStream;
import java.io.PrintStream;

@Service
public class OutputServiceStreams implements OutputService {
    private final PrintStream output;

    public OutputServiceStreams(OutputStream outputStream) {
        output = new PrintStream(outputStream);
    }

    @Override
    public void outString(String s) {
        output.println(s);
    }

}
