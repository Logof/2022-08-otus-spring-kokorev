package ru.otus.homework.hw07.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.homework.hw07.entity.Comment;
import ru.otus.homework.hw07.service.CommentService;
import ru.otus.homework.hw07.service.print.PrintService;

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
        return printService.objectsToPrint(commentService.getAllByIsbn(getCurrBook()));
    }

}
