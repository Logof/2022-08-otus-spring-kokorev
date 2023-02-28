package ru.otus.collectorio.payload.request.category;

import lombok.Getter;
import lombok.Setter;
import ru.otus.collectorio.payload.request.Request;

@Getter
@Setter
public class CategoryRequest implements Request {
    private Long id;
    private String name;
}
