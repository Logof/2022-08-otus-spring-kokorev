package ru.otus.homework.shell.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.otus.homework.entity.Book;
import ru.otus.homework.service.BookService;
import ru.otus.homework.shell.event.book.AddBookEvent;
import ru.otus.homework.shell.event.book.DeleteBookByIdEvent;
import ru.otus.homework.shell.event.book.OutputBookEvent;
import ru.otus.homework.shell.event.book.UpdateBookEvent;

@Component
public class BookShellEventListener {
    private final BookService bookService;


    public BookShellEventListener(BookService bookService) {
        this.bookService = bookService;
    }

    @EventListener
    public void addBookEvent(AddBookEvent event) {
        bookService.add(new Book(event.getIsbn(), event.getTitle()));
    }

    @EventListener
    public void updateBookEvent(UpdateBookEvent event) {
        bookService.update(event.getBook());
    }

    @EventListener
    public void deleteBookByIdEvent(DeleteBookByIdEvent event) {
        bookService.deleteById(event.getBookId());
    }

    @EventListener
    public void outputAllBooksEvent() {
        bookService.getAll();
    }

    @EventListener
    public void outputBookEvent(OutputBookEvent event) {
        bookService.getById(event.getBookId());
    }


}