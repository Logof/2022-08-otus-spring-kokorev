package ru.otus.homework.hw13.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto implements Dto {
    private Long id;
    private String commentText;
    private BookDto book;
}
