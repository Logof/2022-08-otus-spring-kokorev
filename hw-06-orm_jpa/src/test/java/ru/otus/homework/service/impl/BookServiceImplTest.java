package ru.otus.homework.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.homework.entity.Book;
import ru.otus.homework.service.PrintService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тест сервиса по подготовке сущности \"Книга\" к печати")
@SpringBootTest
public class BookServiceImplTest {

    @Autowired
    private PrintService<Book> bookPrintService;

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
        Book book = new Book("ISBN1", "Book 1", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        String stringExpect = bookPrintService.objectToPrint(book);
        String stringActual = "Title: Book 1 (ISBN: ISBN1)\n" +
                "Genre: \n\n" +
                "Authors: \n\n" +
                "---------------------------------------\n";
        assertEquals(stringExpect, stringActual);
    }
}
