package ru.otus.collectorio.payload.response.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.otus.collectorio.payload.response.Response;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryHierarchicalResponse extends CategoryResponse implements Response {
    private Long id;

    private String name;

    private List<CategoryResponse> subCategory;

    public CategoryHierarchicalResponse(Long id, String name) {
        this(id, name, new ArrayList<>());
    }
}
