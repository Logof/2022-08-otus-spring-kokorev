package ru.otus.homework.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.homework.HW6Application;
import ru.otus.homework.entity.Author;
import ru.otus.homework.entity.Book;
import ru.otus.homework.entity.Genre;

import java.util.ArrayList;
import java.util.List;

@DisplayName("Тест сервиса для работы с книгами")
@SpringBootTest(classes = HW6Application.class)
public class BookServiceImplTest {

    @Autowired
    BookServiceImpl bookService;

    @Test
    void saveBookTest() {
        //bookService.saveBook(new Book("122323", "Title", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));

        List<Genre> genreList = new ArrayList<>();
        genreList.add(new Genre(null, "Genre"));

        List<Author> authorList = new ArrayList<>();
        authorList.add(new Author(null, "Author"));

        bookService.saveBook(new Book("121212121212121", "Title", genreList, new ArrayList<>(), new ArrayList<>()));
    }

    void deleteByIsbn() {

    }

    void getAll() {

    }

    void getByIsbn() {

    }
}
