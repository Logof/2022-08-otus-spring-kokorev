package ru.otus.homework.service.print;

import org.springframework.stereotype.Service;
import ru.otus.homework.entity.Comment;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentPrintService implements PrintService<Comment> {
    @Override
    public String objectsToPrint(List<Comment> objects) {
        return String.format("Total comments: %s%s%s", objects.size(), System.lineSeparator(),
                objects.stream().sorted(Comparator.comparing(Comment::getId)).collect(Collectors.toList())
                        .stream().map(book -> objectToPrint(book))
                        .collect(Collectors.joining(System.lineSeparator(), "", "")));
    }

    @Override
    public String objectToPrint(Comment object) {
        return String.format("\t%s (id=%s)", object.getCommentText(), object.getId());
    }
}
