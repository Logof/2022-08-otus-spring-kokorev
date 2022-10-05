package ru.otus.homework.shell.event.book;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class DeleteBookByIdEvent extends ApplicationEvent {

    private final String bookId;

    public DeleteBookByIdEvent(String bookId) {
        super(bookId);
        this.bookId = bookId;
    }
}
