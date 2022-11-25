package ru.otus.homework.hw08.entity;

import lombok.*;

@Getter
@Setter
public class Comment {
    private Long id;

    private String commentText;

    public Comment(String commentText) {
        this.id = System.currentTimeMillis();
        this.commentText = commentText;
    }
}
