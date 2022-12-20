package ru.otus.homework.hw08.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.hw08.entity.Comment;
import ru.otus.homework.hw08.service.CommentService;
import ru.otus.homework.hw08.service.print.PrintService;

@ShellComponent
public class CommentShell extends CommonShell {

    private final CommentService commentService;

    private final PrintService<Comment> printService;

    public CommentShell(CommentService commentService, PrintService<Comment> printService) {
        this.commentService = commentService;
        this.printService = printService;
    }

    @ShellMethod(value = "Output all comments for selected book", key = "print-comments")
    @ShellMethodAvailability(value = "isEmptyIsbn")
    public String outputCommentsForSelectedBook() {
        return printService.objectsToPrint(commentService.getCommentsByIsbn(getCurrBook()));
    }

    @ShellMethod(value = "Output all comments for selected book", key = "delete-comment-id")
    @ShellMethodAvailability(value = "isEmptyIsbn")
    public void outputCommentsForSelectedBook(@ShellOption int commentId) {
        commentService.deleteCommentByIndex(getCurrBook(), commentId);
    }

    @ShellMethod(value = "Add an author to selected book. Accepts full name", key = "add-comment")
    @ShellMethodAvailability(value = "isEmptyIsbn")
    public void addCommentToBook(@ShellOption String commentText) {
        commentService.addCommentToBook(getCurrBook(), commentText);
    }

}
