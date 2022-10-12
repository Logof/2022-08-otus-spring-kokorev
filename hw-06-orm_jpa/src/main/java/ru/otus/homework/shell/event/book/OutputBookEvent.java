package ru.otus.homework.shell.event.book;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class OutputBookEvent extends ApplicationEvent {

    private final String bookId;

    public OutputBookEvent(String bookId) {
        super(bookId);
        this.bookId = bookId;
    }
}
