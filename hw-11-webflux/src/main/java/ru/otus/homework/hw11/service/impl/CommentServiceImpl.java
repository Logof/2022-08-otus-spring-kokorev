package ru.otus.homework.hw11.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.homework.hw11.entity.Book;
import ru.otus.homework.hw11.entity.Comment;
import ru.otus.homework.hw11.exception.DataNotFountException;
import ru.otus.homework.hw11.exception.DeleteDataException;
import ru.otus.homework.hw11.exception.FieldRequiredException;
import ru.otus.homework.hw11.repository.BookRepository;
import ru.otus.homework.hw11.repository.CommentRepository;
import ru.otus.homework.hw11.service.CommentService;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final BookRepository bookRepository;

    public CommentServiceImpl(CommentRepository commentRepository, BookRepository bookRepository) {
        this.commentRepository = commentRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Comment> getCommentsByIsbn(String isbn) {
        return commentRepository.findAllByBook_Isbn(isbn);
    }

    @Override
    public void deleteCommentByIndex(String isbn, int index) throws DeleteDataException {
        List<Comment> commentList = commentRepository.findAllByBook_Isbn(isbn);

        if (index > commentList.size()-1) {
            throw new DeleteDataException("deleted comment not found");
        }
        commentRepository.delete(commentList.get(index));
    }

    @Override
    @Transactional
    public void addCommentToBook(String isbn, String commentText) {
        Book book = bookRepository.findById(isbn).orElseThrow(() -> new DataNotFountException("Book not found"));
        if (commentText == null || commentText.isEmpty()) {
            throw new FieldRequiredException("commentText");
        }
        Comment comment = new Comment(commentText, book);

        commentRepository.save(comment);
    }
}