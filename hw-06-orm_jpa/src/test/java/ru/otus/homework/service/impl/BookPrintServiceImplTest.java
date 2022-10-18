package ru.otus.homework.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ru.otus.homework.entity.Author;
import ru.otus.homework.entity.Book;
import ru.otus.homework.entity.Comment;
import ru.otus.homework.entity.Genre;
import ru.otus.homework.service.print.BookPrintService;
import ru.otus.homework.service.print.PrintService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тест сервиса по подготовке сущности \"Книга\" к печати")
public class BookPrintServiceImplTest {

    @Mock
    PrintService<Genre> genrePrintService;
    @Mock
    PrintService<Author> authorPrintService;
    @Mock
    PrintService<Comment> commentPrintService;

    private final PrintService<Book> bookPrintService
            = new BookPrintService(genrePrintService, authorPrintService, commentPrintService);

    @DisplayName("Подготовка к печати списка книг")
    @Test
    public void verificationPreparingPrintBooksList() {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("ISBN1", "Book 1", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        bookList.add(new Book("ISBN2", "Book 2", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        bookList.add(new Book("ISBN3", "Book 3", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));

        String stringExpect = bookPrintService.objectsToPrint(bookList);
        String stringActual = "Total books: 3" + System.lineSeparator() + "Title: Book 1 (ISBN: ISBN1)\n" +
                "Genre: \n\n" +
                "Authors: \n\n" +
                "Comments: \n\n" +
                "---------------------------------------\n\n" +
                "Title: Book 2 (ISBN: ISBN2)\n" +
                "Genre: \n\n" +
                "Authors: \n\n" +
                "Comments: \n\n" +
                "---------------------------------------\n\n" +
                "Title: Book 3 (ISBN: ISBN3)\n" +
                "Genre: \n\n" +
                "Authors: \n\n" +
                "Comments: \n\n" +
                "---------------------------------------\n";
        assertEquals(stringExpect, stringActual);
    }

    @DisplayName("Подготовка к печати книги")
    @Test
    public void verificationPreparingPrintBook() {
        Book book = new Book("ISBN1", "Book 1", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        String stringExpect = bookPrintService.objectToPrint(book);
        String stringActual = "Title: Book 1 (ISBN: ISBN1)\n" +
                "Genre: \n\n" +
                "Authors: \n\n" +
                "Comments: \n\n" +
                "---------------------------------------\n";
        assertEquals(stringExpect, stringActual);
    }
}
