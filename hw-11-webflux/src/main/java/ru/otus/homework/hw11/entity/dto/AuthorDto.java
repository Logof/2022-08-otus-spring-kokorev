package ru.otus.homework.hw11.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {
    private UUID id;
    private String fullName;

    public AuthorDto(String fullName) {
        this.id = null;
        this.fullName = fullName;
    }
}
