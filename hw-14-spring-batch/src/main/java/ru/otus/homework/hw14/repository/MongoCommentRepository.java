package ru.otus.homework.hw14.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework.hw14.entity.mongo.CommentDocument;

public interface MongoCommentRepository extends MongoRepository<CommentDocument, String> {
}
