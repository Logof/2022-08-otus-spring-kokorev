package ru.otus.homework.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.homework.hw01.service.OutputService;
import ru.otus.homework.hw01.service.OutputServiceImpl;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class OutputServiceImplTests {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("Тестирование сервиса вывода сообщений")
    public void testingOutputService() {
        OutputService outputService = new OutputServiceImpl();
        outputService.messageOutput("Hello world");
        assertEquals(outputStreamCaptor.toString(), "Hello world\n");
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
