package ru.otus.homework.hw07.service.print;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ru.otus.homework.hw07.entity.dto.AuthorDto;
import ru.otus.homework.hw07.entity.dto.BookDto;
import ru.otus.homework.hw07.entity.dto.GenreDto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тест сервиса по подготовке сущности \"Книга\" к печати")
public class BookPrintServiceTest {

    @Mock
    private PrintService<GenreDto> genrePrintService;

    @Mock
    private PrintService<AuthorDto> authorPrintService;

    private final BookPrintService bookPrintService =
            new BookPrintService(genrePrintService, authorPrintService);

    @DisplayName("Подготовка к печати списка книг")
    @Test
    public void verificationPreparingPrintBooksList() {
        List<BookDto> bookList = new ArrayList<>();
        bookList.add(new BookDto("ISBN1", "Book 1"));
        bookList.add(new BookDto("ISBN2", "Book 2"));
        bookList.add(new BookDto("ISBN3", "Book 3"));

        String stringExpect = bookPrintService.objectsToPrint(bookList);
        String stringActual = "Total books: 3" + System.lineSeparator() +
                "Title: Book 1 (ISBN: ISBN1)" + System.lineSeparator() +
                "Genre: " + System.lineSeparator() + System.lineSeparator() +
                "Authors: " + System.lineSeparator() + System.lineSeparator() +
                "---------------------------------------" + System.lineSeparator() + System.lineSeparator() +
                "Title: Book 2 (ISBN: ISBN2)" + System.lineSeparator() +
                "Genre: " + System.lineSeparator() + System.lineSeparator() +
                "Authors: " + System.lineSeparator() + System.lineSeparator() +
                "---------------------------------------" + System.lineSeparator() + System.lineSeparator() +
                "Title: Book 3 (ISBN: ISBN3)" + System.lineSeparator() +
                "Genre: " + System.lineSeparator() + System.lineSeparator() +
                "Authors: " + System.lineSeparator() + System.lineSeparator() +
                "---------------------------------------" + System.lineSeparator();
        assertEquals(stringExpect, stringActual);
    }


    @DisplayName("Подготовка к печати книги")
    @Test
    public void verificationPreparingPrintBook() {
        BookDto book = new BookDto("ISBN1", "Book 1");
        String stringExpect = bookPrintService.objectToPrint(book);
        String stringActual = "Title: Book 1 (ISBN: ISBN1)" + System.lineSeparator() +
                "Genre: " + System.lineSeparator() + System.lineSeparator() +
                "Authors: " + System.lineSeparator() + System.lineSeparator() +
                "---------------------------------------" + System.lineSeparator();
        assertEquals(stringExpect, stringActual);
    }
}
