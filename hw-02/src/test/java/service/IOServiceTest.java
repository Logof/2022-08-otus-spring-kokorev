package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ru.otus.homework.service.IOServiceImpl;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IOServiceTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Mock
    private InputStream scanner;

    @BeforeEach
    public void setup(){
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @DisplayName("Проверка корректного вывода сообщений")
    @Test
    public void shouldCorrectlyDisplayMessages(){
        IOServiceImpl ioService = new IOServiceImpl(new PrintStream(outputStreamCaptor), scanner);

        ioService.messageOutput("Hello world");
        assertEquals(outputStreamCaptor.toString(), "Hello world\n");
    }
}
