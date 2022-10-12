package ru.otus.homework.shell.event.book;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class AddBookEvent extends ApplicationEvent {

    private final String isbn;
    private final String title;

    public AddBookEvent(String isbn, String title) {
        super("");
        this.isbn = isbn;
        this.title = title;
    }
}
