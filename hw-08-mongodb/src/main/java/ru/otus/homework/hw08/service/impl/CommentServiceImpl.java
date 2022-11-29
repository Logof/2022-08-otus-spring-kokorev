package ru.otus.homework.hw08.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.homework.hw08.entity.Book;
import ru.otus.homework.hw08.entity.Comment;
import ru.otus.homework.hw08.exception.DataNotFountException;
import ru.otus.homework.hw08.repository.BookRepository;
import ru.otus.homework.hw08.service.CommentService;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final BookRepository bookRepository;

    public CommentServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Comment> getCommentsByIsbn(String isbn) {
        Book book = bookRepository.findById(isbn).orElseThrow(() -> new DataNotFountException("Book not found"));
        return book.getComments();
    }

    @Override
    public Book deleteCommentByIndex(String isbn, int index) {
        Book book = bookRepository.findById(isbn).orElseThrow(() -> new DataNotFountException("Book not found"));
        book.getComments().remove(index);
        return bookRepository.save(book);
    }
}
