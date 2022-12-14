package ru.otus.homework.hw11.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.homework.hw11.entity.Book;
import ru.otus.homework.hw11.entity.Comment;

public interface CommentRepository extends ReactiveMongoRepository<Comment, String> {

    Flux<Comment> findAllByBook_Isbn(String isbn);

    Mono<Void> deleteByBook(Book book);
}
