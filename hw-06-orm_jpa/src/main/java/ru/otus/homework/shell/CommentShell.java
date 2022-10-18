package ru.otus.homework.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.service.CommentService;

@ShellComponent
public class CommentShell {

    private final CommonShell commonShell;

    private final CommentService commentService;

    public CommentShell(CommonShell commonShell, CommentService commentService) {
        this.commonShell = commonShell;
        this.commentService = commentService;
    }

    @ShellMethod(value = "Output all comments for selected book", key = "print-comment")
    @ShellMethodAvailability(value = "isEmptyIsbn")
    public void outputCommentsForSelectedBook() {
        commentService.getAllByIsbn(commonShell.getCurrBook());
    }

    @ShellMethod(value = "Add comment for selected book", key = "add-comment")
    @ShellMethodAvailability(value = "isEmptyIsbn")
    public void addCommentToSelectedBook(@ShellOption(help = "comment text") String commentText) {
        commentService.add(commonShell.getCurrBook(), commentText);
    }
}
