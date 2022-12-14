package ru.otus.homework.hw08.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework.hw08.entity.Book;
import ru.otus.homework.hw08.entity.Comment;

import java.util.List;
import java.util.UUID;

public interface CommentRepository extends MongoRepository<Comment, UUID> {

    List<Comment> findAllByBook_Isbn(String isbn);

    void deleteByBook(Book book);
}
