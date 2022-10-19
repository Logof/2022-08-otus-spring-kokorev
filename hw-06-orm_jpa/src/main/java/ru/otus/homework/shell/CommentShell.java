package ru.otus.homework.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
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

    @ShellMethod(value = "Add comment for selected book", key = "add-comment")
    @ShellMethodAvailability(value = "isEmptyIsbn")
    public void addCommentToSelectedBook(@ShellOption(help = "comment text") String commentText) {
        commentService.add(getCurrBook(), commentText);
    }
}
