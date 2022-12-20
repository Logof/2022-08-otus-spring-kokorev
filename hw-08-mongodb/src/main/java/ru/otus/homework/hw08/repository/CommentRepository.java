package ru.otus.homework.hw08.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework.hw08.entity.Book;
import ru.otus.homework.hw08.entity.Comment;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {

    List<Comment> findAllByBook_Isbn(String isbn);

    void deleteByBook(Book book);
}
