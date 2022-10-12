package ru.otus.homework.shell.event.links;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.Map;

@Getter
public class RemoveTheGenreFromTheBookEvent extends ApplicationEvent {
    private final String isbn;
    private final long genreId;

    public RemoveTheGenreFromTheBookEvent(String isbn, long genreId) {
        super(Map.of("isbn", isbn, "genreId", genreId));
        this.isbn = isbn;
        this.genreId = genreId;
    }
}
