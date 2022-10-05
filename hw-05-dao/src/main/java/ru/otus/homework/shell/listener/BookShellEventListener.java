package ru.otus.homework.shell.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.otus.homework.service.BookService;
import ru.otus.homework.shell.event.book.AddBookEvent;
import ru.otus.homework.shell.event.book.DeleteBookByIdEvent;
import ru.otus.homework.shell.event.book.OutputAllBooksEvent;
import ru.otus.homework.shell.event.book.OutputBookEvent;

@Component
public class BookShellEventListener {
    private final BookService bookService;


    public BookShellEventListener(BookService bookService) {
        this.bookService = bookService;
    }

    @EventListener
    public void addBookEvent(AddBookEvent event) {
        bookService.add(event.getIsbn(), event.getTitle());
    }

    @EventListener
    public void deleteBookByIdEvent(DeleteBookByIdEvent event) {
        bookService.delete(event.getBookId());
    }

    @EventListener
    public void outputAllBooksEvent(OutputAllBooksEvent event) {
        bookService.outputAll();
    }

    @EventListener
    public void outputBookEvent(OutputBookEvent event) {
        bookService.output(event.getBookId());
    }


}