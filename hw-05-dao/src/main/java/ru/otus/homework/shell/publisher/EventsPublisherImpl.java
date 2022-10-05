package ru.otus.homework.shell.publisher;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import ru.otus.homework.shell.event.author.AddAuthorEvent;
import ru.otus.homework.shell.event.author.DeleteAuthorByIdEvent;
import ru.otus.homework.shell.event.author.OutputAllAuthorsEvent;
import ru.otus.homework.shell.event.book.AddBookEvent;
import ru.otus.homework.shell.event.book.DeleteBookByIdEvent;
import ru.otus.homework.shell.event.book.OutputAllBooksEvent;
import ru.otus.homework.shell.event.book.OutputBookEvent;
import ru.otus.homework.shell.event.genre.AddGenreEvent;
import ru.otus.homework.shell.event.genre.DeleteGenreByIdEvent;
import ru.otus.homework.shell.event.genre.OutputAllGenresEvent;
import ru.otus.homework.shell.event.links.AddAnAuthorToABookEvent;
import ru.otus.homework.shell.event.links.AddAnGenreToABookEvent;
import ru.otus.homework.shell.event.links.RemoveTheAuthorFromTheBookEvent;
import ru.otus.homework.shell.event.links.RemoveTheGenreFromTheBookEvent;

@Service
public class EventsPublisherImpl implements EventsPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public EventsPublisherImpl(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void outputBook(String isbn) {
        applicationEventPublisher.publishEvent(new OutputBookEvent(isbn));
    }

    @Override
    public void outputAllBooks() {
        applicationEventPublisher.publishEvent(new OutputAllBooksEvent());
    }

    @Override
    public void addBook(String isbn, String bookName) {
        applicationEventPublisher.publishEvent(new AddBookEvent(isbn, bookName));
    }

    @Override
    public void deleteBookById(String isbn) {
        applicationEventPublisher.publishEvent(new DeleteBookByIdEvent(isbn));
    }

    @Override
    public void addAnAuthorToABook(String isbn, long authorId) {
        applicationEventPublisher.publishEvent(new AddAnAuthorToABookEvent(isbn, authorId));
    }

    @Override
    public void addAnGenreToABook(String isbn, long genreId) {
        applicationEventPublisher.publishEvent(new AddAnGenreToABookEvent(isbn, genreId));
    }

    @Override
    public void removeTheAuthorFromTheBook(String isbn, long authorId) {
        applicationEventPublisher.publishEvent(new RemoveTheAuthorFromTheBookEvent(isbn, authorId));
    }

    @Override
    public void removeTheGenreFromTheBook(String isbn, long genreId) {
        applicationEventPublisher.publishEvent(new RemoveTheGenreFromTheBookEvent(isbn, genreId));
    }

    @Override
    public void outputAllAuthors() {
        applicationEventPublisher.publishEvent(new OutputAllAuthorsEvent());
    }

    @Override
    public void addAuthor(String fullName) {
        applicationEventPublisher.publishEvent(new AddAuthorEvent(fullName));
    }

    @Override
    public void deleteAuthorById(long authorId) {
        applicationEventPublisher.publishEvent(new DeleteAuthorByIdEvent(authorId));
    }

    @Override
    public void outputAllGenres() {
        applicationEventPublisher.publishEvent(new OutputAllGenresEvent());
    }

    @Override
    public void addGenre(String genreName) {
        applicationEventPublisher.publishEvent(new AddGenreEvent(genreName));
    }

    @Override
    public void deleteGenreById(long genreId) {
        applicationEventPublisher.publishEvent(new DeleteGenreByIdEvent(genreId));
    }

}
