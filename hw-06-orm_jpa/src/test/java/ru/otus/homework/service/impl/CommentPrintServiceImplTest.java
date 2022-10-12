package ru.otus.homework.service.impl;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.homework.entity.Comment;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тест сервиса по подготовке сущности \"Комментарий\" к печати")
public class CommentPrintServiceImplTest {

    private final CommentPrintService commentPrintService = new CommentPrintService();

    @DisplayName("Подготовка к печати списка комментариев")
    @Test
    public void verificationPreparingPrintAuthorsList() {
        List<Comment> authorList = new ArrayList<>();
        authorList.add(new Comment(1L, "Comment 1"));
        authorList.add(new Comment(2L, "Comment 2"));
        authorList.add(new Comment(3L, "Comment 3"));

        String stringExpect = commentPrintService.objectsToPrint(authorList);
        String stringActual = "Total comments: 3" + System.lineSeparator() + "\tComment 1 (id=1)" + System.lineSeparator() + "\tComment 2 (id=2)" + System.lineSeparator() + "\tComment 3 (id=3)";
        assertEquals(stringExpect, stringActual);
    }


    @DisplayName("Подготовка к печати комментария")
    @Test
    public void verificationPreparingPrintAuthor() {
        Comment author = new Comment(1L, "Comment 1");
        String stringExpect = commentPrintService.objectToPrint(author);
        String stringActual = "\tComment 1 (id=1)";
        assertEquals(stringExpect, stringActual);
    }
}
