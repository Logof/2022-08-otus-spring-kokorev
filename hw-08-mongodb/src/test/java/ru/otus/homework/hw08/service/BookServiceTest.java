package ru.otus.homework.hw08.service;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import ru.otus.homework.hw08.entity.Book;
import ru.otus.homework.hw08.service.impl.BookServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
@Import(BookServiceImpl.class)
public class BookServiceTest {

    private static final String COLLECTION_NAME = Book.class.getSimpleName().toLowerCase();
    @Autowired
    private BookService bookService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void updateTitleTest() {
        mongoTemplate.remove(new Query(), COLLECTION_NAME);
        MongoCollection<Document> documentList = mongoTemplate.getCollection(COLLECTION_NAME);
        assertEquals(documentList.countDocuments(), 0);

        Book book = new Book("1234-12345-123-0", "Title");
        bookService.add(book);

        book.setTitle("New Title");
        bookService.updateTitle(book.getIsbn(), book.getTitle());
        Book expectedBook = mongoTemplate.findById("1234-12345-123-0", Book.class);

        assertEquals(expectedBook, book);

    }

    @Test
    public void addTest() {
        mongoTemplate.remove(new Query(), COLLECTION_NAME);
        MongoCollection<Document> documentList = mongoTemplate.getCollection(COLLECTION_NAME);
        assertEquals(documentList.countDocuments(), 0);

        Book book = new Book("1234-12345-123-0", "Title");
        Book expectedBook = bookService.add(book);
        assertEquals(expectedBook, book);

        documentList = mongoTemplate.getCollection(COLLECTION_NAME);
        assertEquals(documentList.countDocuments(), 1);
    }

    @Test
    public void deleteByIsbnTest() {
        mongoTemplate.remove(new Query(), COLLECTION_NAME);
        MongoCollection<Document> documentList = mongoTemplate.getCollection(COLLECTION_NAME);
        assertEquals(documentList.countDocuments(), 0);

        Book book = new Book("1234-12345-123-0", "Title");
        Book expectedBook = bookService.add(book);
        documentList = mongoTemplate.getCollection(COLLECTION_NAME);
        assertEquals(documentList.countDocuments(), 1);
        assertEquals(expectedBook, book);

        bookService.deleteByIsbn(book.getIsbn());
        documentList = mongoTemplate.getCollection(COLLECTION_NAME);
        assertEquals(documentList.countDocuments(), 0);
    }

    @Test
    public void getAllTest() {

    }

    @Test
    public void getByIsbnTest() {

    }

    @Test
    public void getAllByAuthorTest() {

    }

    @Test
    public void getAllByGenreTest() {

    }

    @Test
    public void addGenreToBookTest() {

    }

    @Test
    public void addAuthorToBookTest() {

    }

    @Test
    public void addCommentToBookTest() {

    }

    @Test
    public void getCommentsByIsbnTest() {

    }

    @Test
    public void deleteCommentByIndexTest() {

    }
}
