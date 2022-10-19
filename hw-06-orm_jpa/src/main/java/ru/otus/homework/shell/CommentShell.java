package ru.otus.homework.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.homework.service.CommentService;

@ShellComponent
public class CommentShell extends CommonShell {

    private final CommentService commentService;

    public CommentShell(CommentService commentService) {
        this.commentService = commentService;
    }

    @ShellMethod(value = "Output all comments for selected book", key = "print-comment")
    @ShellMethodAvailability(value = "isEmptyIsbn")
    public void outputCommentsForSelectedBook() {
        commentService.getAllByIsbn(getCurrBook());
    }

}
