package ru.otus.homework.hw08.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import ru.otus.homework.hw08.entity.Book;
import ru.otus.homework.hw08.entity.Comment;
import ru.otus.homework.hw08.service.impl.CommentServiceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@Import(CommentServiceImpl.class)
public class CommentServiceTest {

    private static final String COLLECTION_NAME = Book.class.getSimpleName().toLowerCase();

    @Autowired
    private CommentService commentService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void getCommentsByIsbnTest() {
        mongoTemplate.remove(new Query(), COLLECTION_NAME);

        Book bookWithComment = new Book("1234-0", "Title");
        List<Comment> commentList = new ArrayList<>();
        commentList.add(new Comment("new comment"));
        bookWithComment.setComments(commentList);

        mongoTemplate.save(bookWithComment);

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
        List<Comment> commentList = new ArrayList<>();
        commentList.add(new Comment("new comment"));
        bookWithComment.setComments(commentList);

        mongoTemplate.save(bookWithComment);

        Book expectedBook = commentService.deleteCommentByIndex("1234-0", 0);

        boolean isCommentFound = false;

        for (Comment comment: expectedBook.getComments()) {
            if (comment.getCommentText().equals("new comment")) {
                isCommentFound = true;
            }
        }

        assertFalse(isCommentFound);
    }
}
