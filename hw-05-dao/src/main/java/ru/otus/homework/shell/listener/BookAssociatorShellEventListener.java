package ru.otus.homework.shell.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.otus.homework.service.BookAssociationService;
import ru.otus.homework.shell.event.links.AddAnAuthorToABookEvent;
import ru.otus.homework.shell.event.links.AddAnGenreToABookEvent;
import ru.otus.homework.shell.event.links.RemoveTheAuthorFromTheBookEvent;
import ru.otus.homework.shell.event.links.RemoveTheGenreFromTheBookEvent;

@Component
public class BookAssociatorShellEventListener {
    private final BookAssociationService bookAssociationService;

    public BookAssociatorShellEventListener(BookAssociationService bookAssociationService) {
        this.bookAssociationService = bookAssociationService;
    }

    @EventListener
    public void addAnAuthorToABookEvent(AddAnAuthorToABookEvent event) {
        bookAssociationService.addAuthorAssoc(event.getIsbn(), event.getAuthorId());
    }

    @EventListener
    public void addAnGenreToABookEvent(AddAnGenreToABookEvent event) {
        bookAssociationService.addGenreAssoc(event.getIsbn(), event.getGenreId());
    }

    @EventListener
    public void removeTheAuthorFromTheBookEvent(RemoveTheAuthorFromTheBookEvent event) {
        bookAssociationService.removeAuthor(event.getIsbn(), event.getAuthorId());
    }

    @EventListener
    public void removeTheGenreFromTheBookEvent(RemoveTheGenreFromTheBookEvent event) {
        bookAssociationService.removeGenre(event.getIsbn(), event.getGenreId());
    }

}
