package ru.otus.homework.service.print;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.homework.hw06.entity.Book;
import ru.otus.homework.hw06.entity.Comment;
import ru.otus.homework.hw06.service.print.CommentPrintService;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тест сервиса по подготовке сущности \"Комментарий\" к печати")
public class CommentPrintServiceTest {

    private final CommentPrintService commentPrintService = new CommentPrintService();

    @DisplayName("Подготовка к печати списка комментариев")
    @Test
    public void verificationPreparingPrintBooksList() {
        Set<Comment> authorList = new HashSet<>();
        authorList.add(new Comment(1L, "Comment 1", new Book()));
        authorList.add(new Comment(2L, "Comment 2", new Book()));
        authorList.add(new Comment(3L, "Comment 3", new Book()));
        String stringExpect = commentPrintService.objectsToPrint(authorList);
        String stringActual = "Total comments: 3" + System.lineSeparator() + "\tComment 1 (id=1)" + System.lineSeparator() + "\tComment 2 (id=2)" + System.lineSeparator() + "\tComment 3 (id=3)";
        assertEquals(stringExpect, stringActual);
    }

    @DisplayName("Подготовка к печати комментария")
    @Test
    public void verificationPreparingPrintAuthor() {
        Comment author = new Comment(1L, "Comment 1", new Book());
        String stringExpect = commentPrintService.objectToPrint(author);
        String stringActual = "\tComment 1 (id=1)";
        assertEquals(stringExpect, stringActual);
    }

}
