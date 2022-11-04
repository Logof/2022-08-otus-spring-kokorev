package ru.otus.homework.print;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ru.otus.homework.hw05.entity.Author;
import ru.otus.homework.hw05.entity.Book;
import ru.otus.homework.hw05.entity.Genre;
import ru.otus.homework.hw05.service.print.BookPrintService;
import ru.otus.homework.hw05.service.print.PrintService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тест сервиса по подготовке сущности \"Книга\" к печати")
public class BookPrintServiceTest {

    @Mock
    private PrintService<Genre> genrePrintService;

    @Mock
    private PrintService<Author> authorPrintService;

    private final BookPrintService bookPrintService = new BookPrintService(genrePrintService, authorPrintService);

    @DisplayName("Подготовка к печати списка книг")
    @Test
    public void verificationPreparingPrintBooksList() {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("ISBN1", "Book 1"));
        bookList.add(new Book("ISBN2", "Book 2"));
        bookList.add(new Book("ISBN3", "Book 3"));

        String stringExpect = bookPrintService.objectsToPrint(bookList);
        String stringActual = "Total books: 3" + System.lineSeparator() + "Title: Book 1 (ISBN: ISBN1)\n" +
                "Genre: \n\n" +
                "Authors: \n\n" +
                "---------------------------------------\n\n" +
                "Title: Book 2 (ISBN: ISBN2)\n" +
                "Genre: \n\n" +
                "Authors: \n\n" +
                "---------------------------------------\n\n" +
                "Title: Book 3 (ISBN: ISBN3)\n" +
                "Genre: \n\n" +
                "Authors: \n\n" +
                "---------------------------------------\n";
        assertEquals(stringExpect, stringActual);
    }


    @DisplayName("Подготовка к печати книги")
    @Test
    public void verificationPreparingPrintBook() {
        Book book = new Book("ISBN1", "Book 1");
        String stringExpect = bookPrintService.objectToPrint(book);
        String stringActual = "Title: Book 1 (ISBN: ISBN1)\n" +
                "Genre: \n\n" +
                "Authors: \n\n" +
                "---------------------------------------\n";
        assertEquals(stringExpect, stringActual);
    }
}
