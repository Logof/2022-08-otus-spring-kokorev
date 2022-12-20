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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        mongoTemplate.remove(new Query(), COLLECTION_NAME);
        List<Book> actualBookList = new ArrayList<>();
        Book book1 = bookService.add(new Book("1234-0", "genreName 1"));
        Book book2 = bookService.add(new Book("1234-1", "genreName 2"));
        actualBookList.add(book1);
        actualBookList.add(book2);

        List<Book> expectedBookList = bookService.getAll();

        assertTrue(expectedBookList.equals(actualBookList));
        mongoTemplate.remove(new Query(), COLLECTION_NAME);
    }

    @Test
    public void getByIsbnTest() {
        Book book = bookService.add(new Book("1234-0", "genreName 1"));
        Book expectedBook = bookService.getByIsbn("1234-0");
        assertEquals(expectedBook, book);
        mongoTemplate.remove(new Query(), COLLECTION_NAME);
    }

    @Test
    public void addGenreToBookTest() {
        mongoTemplate.remove(new Query(), COLLECTION_NAME);
        Book book = bookService.add(new Book("1234-0", "Title"));

        Book expectedBook = bookService.addGenreToBook("1234-0", "new genre");
        assertEquals(expectedBook.getGenres().size(), 1);
        assertTrue(expectedBook.getGenres().get(0).getGenreName().equals("new genre"));
    }


    @Test
    public void addAuthorToBookTest() {
        mongoTemplate.remove(new Query(), COLLECTION_NAME);
        Book book = bookService.add(new Book("1234-0", "Title"));

        Book expectedBook = bookService.addAuthorToBook("1234-0", "new Author");

        assertEquals(expectedBook.getAuthors().size(), 1);
        assertTrue(expectedBook.getAuthors().get(0).getFullName().equals("new Author"));
    }

}
