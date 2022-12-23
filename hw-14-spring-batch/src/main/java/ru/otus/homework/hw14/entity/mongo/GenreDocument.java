package ru.otus.homework.hw14.entity.mongo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "genres")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenreDocument {
    @Id
    private String id;

    private String genreName;

    public GenreDocument(String genreName) {
        this.genreName = genreName;
    }
}
