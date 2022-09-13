package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ru.otus.homework.service.IOServiceImpl;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Сервис ввода/вывода ")
public class IOServiceTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Mock
    private Scanner scanner;

    @BeforeEach
    public void setup(){
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @DisplayName("Проверка корректного вывода сообщений")
    @Test
    public void shouldCorrectlyDisplayMessages(){
        IOServiceImpl ioService = new IOServiceImpl();

        ioService.messageOutput("Hello world");
        assertEquals(outputStreamCaptor.toString(), "Hello world\n");
    }
}
