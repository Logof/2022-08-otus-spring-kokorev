package ru.otus.homework.hw08.service.print;

import org.springframework.stereotype.Service;
import ru.otus.homework.hw08.entity.Comment;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentPrintService implements PrintService<Comment> {
    @Override
    public String objectsToPrint(List<Comment> objects) {
        return String.format("Total comment(s): %d%s%s", objects.size(), System.lineSeparator(),
                objects.stream()
                        .map(comment -> String.format("%s (id=%d)", comment.getCommentText(), objects.indexOf(comment)))
                        .collect(Collectors.joining(System.lineSeparator() + "\t", "\t", "")));
    }

    @Override
    public String objectToPrint(Comment object) {
        return null;
    }
}
