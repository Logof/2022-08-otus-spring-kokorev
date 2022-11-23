package ru.otus.homework.hw10.contoller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.homework.hw10.entity.dto.CommentDto;
import ru.otus.homework.hw10.service.CommentService;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CommentRestController {

    private final CommentService commentService;

    @GetMapping(path = "/api/comment/{isbn}")
    public List<CommentDto> getAllCommentByIsbn(@PathVariable("isbn") String isbn) {
        return commentService.getAllByIsbn(isbn);
    }
}
