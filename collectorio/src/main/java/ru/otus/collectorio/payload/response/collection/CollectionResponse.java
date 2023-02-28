package ru.otus.collectorio.payload.response.collection;

import lombok.Getter;
import lombok.Setter;
import ru.otus.collectorio.payload.response.Response;

@Getter
@Setter
public class CollectionResponse implements Response {
    private Long id;

    private String name;

}
