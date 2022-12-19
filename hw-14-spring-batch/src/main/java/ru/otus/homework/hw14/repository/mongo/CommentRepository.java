package ru.otus.homework.hw14.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework.hw14.entity.mongo.Comment;

public interface CommentRepository extends MongoRepository<Comment, String> {
}
