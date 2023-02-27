package ru.otus.collectorio.payload.response.category;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryExtResponse extends CategoryResponse{
    private CategoryResponse parent;
}
