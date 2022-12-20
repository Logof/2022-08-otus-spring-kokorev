package ru.otus.homework.hw11.contoller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.otus.homework.hw11.entity.dto.CommentDto;
import ru.otus.homework.hw11.mapper.CommentMapper;
import ru.otus.homework.hw11.repository.CommentRepository;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CommentRestController {

    private final CommentRepository commentRepository;

    private final CommentMapper commentMapper;

    @GetMapping(path = "/api/comment/{isbn}")
    public Flux<CommentDto> getAllCommentByIsbn(@PathVariable("isbn") String isbn) {
        return commentRepository.findAllByBook_Isbn(isbn).map(comment -> commentMapper.toDto(comment));
    }
}
