package ru.otus.homework.hw07.service.print;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.homework.hw07.entity.dto.BookDto;
import ru.otus.homework.hw07.entity.dto.CommentDto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тест сервиса по подготовке сущности \"Комментарий\" к печати")
public class CommentPrintServiceTest {

    private final CommentPrintService commentPrintService = new CommentPrintService();

    @DisplayName("Подготовка к печати списка комментариев")
    @Test
    public void verificationPreparingPrintBooksList() {
        List<CommentDto> commentList = new ArrayList<>();
        commentList.add(new CommentDto(1L, "Comment 1", new BookDto()));
        commentList.add(new CommentDto(2L, "Comment 2", new BookDto()));
        commentList.add(new CommentDto(3L, "Comment 3", new BookDto()));
        String stringExpect = commentPrintService.objectsToPrint(commentList);
        String stringActual = "Total comments: 3" + System.lineSeparator() + "\tComment 1 (id=1)" + System.lineSeparator() + "\tComment 2 (id=2)" + System.lineSeparator() + "\tComment 3 (id=3)";
        assertEquals(stringExpect, stringActual);
    }

    @DisplayName("Подготовка к печати комментария")
    @Test
    public void verificationPreparingPrintAuthor() {
        CommentDto comment = new CommentDto(1L, "Comment 1", new BookDto());
        String stringExpect = commentPrintService.objectToPrint(comment);
        String stringActual = "\tComment 1 (id=1)";
        assertEquals(stringExpect, stringActual);
    }

}
