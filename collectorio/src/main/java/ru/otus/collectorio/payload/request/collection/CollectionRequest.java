package ru.otus.collectorio.payload.request.collection;

import lombok.Getter;
import lombok.Setter;
import ru.otus.collectorio.payload.request.Request;

@Getter
@Setter
public class CollectionRequest implements Request {

    private Long id;

    private String name;

}
