package ru.otus.homework.hw08.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import ru.otus.homework.hw08.entity.Book;
import ru.otus.homework.hw08.entity.Comment;
import ru.otus.homework.hw08.repository.BookRepository;
import ru.otus.homework.hw08.service.impl.CommentServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@DataMongoTest
@Import(CommentServiceImpl.class)
public class CommentServiceTest {

    private static final String COLLECTION_NAME = Book.class.getSimpleName().toLowerCase();

    @MockBean
    BookRepository bookRepository;

    @Autowired
    private CommentService commentService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void getCommentsByIsbnTest() {
        mongoTemplate.remove(new Query(), COLLECTION_NAME);

        Book bookWithComment = new Book("1234-0", "Title");
        mongoTemplate.save(new Comment("new comment", bookWithComment));

        List<Comment> expectedCommentList = commentService.getCommentsByIsbn("1234-0");

        boolean isCommentFound = false;

        for (Comment comment: expectedCommentList) {
            if (comment.getCommentText().equals("new comment")) {
                isCommentFound = true;
            }
        }

        assertTrue(isCommentFound);
    }

    @Test
    public void deleteCommentByIndexTest() {
        mongoTemplate.remove(new Query(), COLLECTION_NAME);

        Book bookWithComment = new Book("1234-0", "Title");
        mongoTemplate.save(new Comment("new comment", bookWithComment));

        commentService.deleteCommentByIndex("1234-0", 0);

        boolean isCommentFound = false;
        assertFalse(isCommentFound);
    }

    @Test
    public void addCommentToBookTest(){
        mongoTemplate.remove(new Query(), COLLECTION_NAME);
        Book bookWithComment = new Book("1234-0", "Title");

        when(bookRepository.findById(anyString())).thenReturn(Optional.of(bookWithComment));
        commentService.addCommentToBook(bookWithComment.getIsbn(), "New Comment");

        Query query = new Query();
        query.addCriteria(Criteria.where("commentText").is("New Comment"));

        List<Comment> expectedComment = mongoTemplate.find(query, Comment.class);

        assertEquals(expectedComment.size(), 1);
        assertEquals(expectedComment.get(0).getCommentText(), "New Comment");

    }
}
