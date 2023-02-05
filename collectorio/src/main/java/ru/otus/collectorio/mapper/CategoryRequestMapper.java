package ru.otus.collectorio.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.otus.collectorio.entity.Category;
import ru.otus.collectorio.payload.request.category.CategoryRequest;
import ru.otus.collectorio.payload.response.category.CategoryResponse;

@Mapper
public interface CategoryRequestMapper {


    @Mapping(target = "name", source = "name")
    @Mapping(target = "children", ignore = true)
    @Mapping(target = "id", ignore = true)
    Category toCategory(CategoryRequest categoryRequest);

    CategoryResponse toCategoryResponse(Category category);
    /*
    @AfterMapping
    default void calledWithSourceAndTargetType(CategoryRequest source, Category target) {
        if (target.getChildren() == null) {
            target.setChildren(new ArrayList<>());
        }
        Category category = new Category();
        category.setName(source.getName());
        target.getChildren().add(category);
    }
    */
}
