package ru.otus.collectorio.mapper;

import org.mapstruct.Mapper;
import ru.otus.collectorio.entity.Category;
import ru.otus.collectorio.payload.request.category.CategoryRequest;
import ru.otus.collectorio.payload.response.category.CategoryExtResponse;
import ru.otus.collectorio.payload.response.category.CategoryHierarchicalResponse;
import ru.otus.collectorio.payload.response.category.CategoryResponse;

import java.util.*;

@Mapper
public interface CategoryMapper {

    Category toCategory(CategoryRequest categoryRequest);

    CategoryResponse toCategoryResponse(Category category);

    CategoryExtResponse toCategoryExtResponse(Category category);

    default List<CategoryHierarchicalResponse> toCategoryHierarchicalList(List<Category> categories) {
        Map<Long, CategoryHierarchicalResponse> result = new HashMap<>();
        for (Category category : categories) {
            CategoryHierarchicalResponse parent = Objects.isNull(category.getParent()) ?
                    new CategoryHierarchicalResponse(category.getId(), category.getName()) :
                    new CategoryHierarchicalResponse(category.getParent().getId(), category.getParent().getName());
            if (Objects.isNull(result.get(parent.getId()))) {
                if (!Objects.isNull(category.getParent())) {
                    CategoryHierarchicalResponse child = new CategoryHierarchicalResponse(category.getId(), category.getName());
                    parent.getSubCategory().add(child);
                }
                result.put(parent.getId(), parent);
            } else {
                parent = result.get(parent.getId());
                if (!Objects.isNull(category.getParent())) {
                    if (!Objects.isNull(category.getParent())) {
                        CategoryResponse child = new CategoryResponse(category.getId(), category.getName());
                        parent.getSubCategory().add(child);
                    }
                    result.put(parent.getId(), parent);
                }
            }
        }
        return new ArrayList<>(result.values());
    }

}
