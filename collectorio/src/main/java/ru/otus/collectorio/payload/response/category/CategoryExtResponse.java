package ru.otus.collectorio.payload.response.category;

import lombok.Getter;
import lombok.Setter;
import ru.otus.collectorio.payload.response.Response;

@Getter
@Setter
public class CategoryExtResponse extends CategoryResponse implements Response {
    private CategoryResponse parent;
}
