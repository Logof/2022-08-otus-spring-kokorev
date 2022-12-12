package ru.otus.homework.hw11.contoller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.homework.hw11.entity.Comment;
import ru.otus.homework.hw11.service.CommentService;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CommentRestController {

    private final CommentService commentService;

    @GetMapping(path = "/api/comment/{isbn}")
    public List<Comment> getAllCommentByIsbn(@PathVariable("isbn") String isbn) {
        return commentService.getCommentsByIsbn(isbn);
    }
}
