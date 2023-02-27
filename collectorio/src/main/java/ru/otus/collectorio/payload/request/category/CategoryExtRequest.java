package ru.otus.collectorio.payload.request.category;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryExtRequest extends CategoryRequest {
    private CategoryRequest parent;
}
