package ru.otus.collectorio.payload.response.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.otus.collectorio.payload.response.Response;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse implements Response {
    private Long id;

    private String name;

}
