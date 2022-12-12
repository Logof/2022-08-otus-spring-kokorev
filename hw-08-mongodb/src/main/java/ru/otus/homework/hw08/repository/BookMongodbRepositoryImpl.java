package ru.otus.homework.hw08.repository;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import ru.otus.homework.hw08.entity.Book;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Repository
public class BookMongodbRepositoryImpl implements BookMongodbRepository {
    
    private final MongoTemplate mongoTemplate;

    public BookMongodbRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void updateDocumentTitle(String id, String title) {
        Query query = new Query().addCriteria(where("_id").is(id));
        Update update = new Update();
        update.set("title", title);
        mongoTemplate.update(Book.class).matching(query).apply(update).first();
    }
}
