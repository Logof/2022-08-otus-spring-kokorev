package ru.otus.homework.service.print;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.homework.entity.Book;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тест сервиса по подготовке сущности \"Книга\" к печати")
public class BookPrintServiceTest {
    private final PrintService bookPrintService = new BookPrintService();

    @DisplayName("Подготовка к печати списка книг")
    @Test
    public void verificationPreparingPrintBooksList() {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("ISBN1", "Book 1", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        bookList.add(new Book("ISBN2", "Book 2", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        bookList.add(new Book("ISBN3", "Book 3", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));

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
        Book book = new Book("ISBN1", "Book 1", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        String stringExpect = bookPrintService.objectToPrint(book);
        String stringActual = "Title: Book 1 (ISBN: ISBN1)" + System.lineSeparator() +
                "Genre: " + System.lineSeparator() + System.lineSeparator() +
                "Authors: " + System.lineSeparator() + System.lineSeparator() +
                "---------------------------------------" + System.lineSeparator();
        assertEquals(stringExpect, stringActual);
    }
}
