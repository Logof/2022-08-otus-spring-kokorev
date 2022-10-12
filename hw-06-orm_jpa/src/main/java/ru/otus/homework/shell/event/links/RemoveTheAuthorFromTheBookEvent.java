package ru.otus.homework.shell.event.links;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.Map;

@Getter
public class RemoveTheAuthorFromTheBookEvent extends ApplicationEvent {

    private final String isbn;
    private final long authorId;

    public RemoveTheAuthorFromTheBookEvent(String isbn, long authorId) {
        super(Map.of("isbn", isbn, "authorId", authorId));
        this.isbn = isbn;
        this.authorId = authorId;
    }
}
