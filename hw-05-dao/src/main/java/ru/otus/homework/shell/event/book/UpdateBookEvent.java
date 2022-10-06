package ru.otus.homework.shell.event.book;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import ru.otus.homework.entity.Book;

@Getter
public class UpdateBookEvent extends ApplicationEvent {

    private final String isbn;
    private final Book book;

    public UpdateBookEvent(String isbn, Book book) {
        super("");
        this.isbn = isbn;
        this.book = book;
    }

}