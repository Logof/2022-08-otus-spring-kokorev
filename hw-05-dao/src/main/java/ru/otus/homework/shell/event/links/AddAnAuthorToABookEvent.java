package ru.otus.homework.shell.event.links;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.Map;

@Getter
public class AddAnAuthorToABookEvent extends ApplicationEvent {

    private final String isbn;
    private final long authorId;

    public AddAnAuthorToABookEvent(String isbn, long authorId) {
        super(Map.of("isbn", isbn, "authorId", authorId));
        this.isbn = isbn;
        this.authorId = authorId;
    }
}
