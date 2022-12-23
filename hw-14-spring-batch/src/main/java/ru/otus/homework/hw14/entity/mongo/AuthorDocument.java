package ru.otus.homework.hw14.entity.mongo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "authors")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDocument {
    @Id
    private String id;

    private String fullName;

    public AuthorDocument(String fullName) {
        this.fullName = fullName;
    }
}
