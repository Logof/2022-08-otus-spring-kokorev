package ru.otus.homework.service.print;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ru.otus.homework.entity.Author;
import ru.otus.homework.entity.Book;
import ru.otus.homework.entity.Genre;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тест сервиса по подготовке сущьности \"Книга\" к печати")
public class BookPrintServiceTest {

    @Mock
    private PrintService<Genre> genrePrintService;

    @Mock
    private PrintService<Author> authorPrintService;

    private final BookPrintService bookPrintService =
            new BookPrintService(genrePrintService, authorPrintService);

    @DisplayName("Подготовка к печати списка книг")
    @Test
    public void verificationPreparingPrintBooksList() {
        Set<Book> bookList = new HashSet<>();
        bookList.add(new Book("ISBN1", "Book 1"));
        bookList.add(new Book("ISBN2", "Book 2"));
        bookList.add(new Book("ISBN3", "Book 3"));

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
                "---------------------------------------"+ System.lineSeparator();
        assertEquals(stringExpect, stringActual);
    }


    @DisplayName("Подготовка к печати книги")
    @Test
    public void verificationPreparingPrintBook() {
        Book book = new Book("ISBN1", "Book 1");
        String stringExpect = bookPrintService.objectToPrint(book);
        String stringActual = "Title: Book 1 (ISBN: ISBN1)" + System.lineSeparator() +
                "Genre: " + System.lineSeparator() + System.lineSeparator() +
                "Authors: " + System.lineSeparator() + System.lineSeparator() +
                "---------------------------------------"+ System.lineSeparator();
        assertEquals(stringExpect, stringActual);
    }
}
