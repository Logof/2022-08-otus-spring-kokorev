package ru.otus.homework.hw11.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework.hw11.entity.Comment;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {

    List<Comment> findAllByBook_Isbn(String isbn);
}
