package ru.otus.homework.hw06.service.print;

import org.springframework.stereotype.Service;
import ru.otus.homework.hw06.entity.Comment;

import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentPrintService implements PrintService<Comment> {
    @Override
    public String objectsToPrint(Set<Comment> objects) {
        return String.format("Total comments: %d%s%s", objects.size(), System.lineSeparator(),
                objects.stream().sorted(Comparator.comparing(Comment::getId))
                        .map(comment -> String.format("%s (id=%d)", comment.getCommentText(), comment.getId()))
                        .collect(Collectors.joining(System.lineSeparator() + "\t", "\t", "")));
    }

    @Override
    public String objectToPrint(Comment object) {
        return String.format("\t%s (id=%d)", object.getCommentText(), object.getId());
    }
}
