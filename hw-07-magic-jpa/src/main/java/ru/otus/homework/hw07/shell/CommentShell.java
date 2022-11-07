package ru.otus.homework.hw07.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.hw07.entity.dto.CommentDto;
import ru.otus.homework.hw07.service.CommentService;
import ru.otus.homework.hw07.service.print.PrintService;

@ShellComponent
public class CommentShell extends CommonShell {

    private final CommentService commentService;

    private final PrintService<CommentDto> printService;

    public CommentShell(CommentService commentService, PrintService<CommentDto> printService) {
        this.commentService = commentService;
        this.printService = printService;
    }

    @ShellMethod(value = "Output all comments for selected book", key = "print-comments")
    @ShellMethodAvailability(value = "isEmptyIsbn")
    public String outputCommentsForSelectedBook() {
        return printService.objectsToPrint(commentService.getAllByIsbn(getCurrBook()));
    }

    @ShellMethod(value = "Output all comments for selected book", key = "delete-comment-id")
    @ShellMethodAvailability(value = "isEmptyIsbn")
    public void outputCommentsForSelectedBook(@ShellOption long commentId) {
        commentService.delete(commentId);
    }

}
