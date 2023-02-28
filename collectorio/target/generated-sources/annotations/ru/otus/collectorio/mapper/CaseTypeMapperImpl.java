package ru.otus.collectorio.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.otus.collectorio.entity.CaseType;
import ru.otus.collectorio.entity.Category;
import ru.otus.collectorio.payload.request.caseType.CaseTypeExtRequest;
import ru.otus.collectorio.payload.request.caseType.CaseTypeRequest;
import ru.otus.collectorio.payload.request.category.CategoryRequest;
import ru.otus.collectorio.payload.response.caseType.CaseTypeExtResponse;
import ru.otus.collectorio.payload.response.caseType.CaseTypeResponse;
import ru.otus.collectorio.payload.response.category.CategoryResponse;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-28T00:53:30+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.18 (Oracle Corporation)"
)
@Component
public class CaseTypeMapperImpl implements CaseTypeMapper {

    @Override
    public CaseType toCaseType(CaseTypeRequest categoryRequest) {
        if ( categoryRequest == null ) {
            return null;
        }

        CaseType caseType = new CaseType();

        caseType.setId( categoryRequest.getId() );
        caseType.setName( categoryRequest.getName() );

        return caseType;
    }

    @Override
    public CaseType toCaseType(CaseTypeExtRequest categoryExtRequest) {
        if ( categoryExtRequest == null ) {
            return null;
        }

        CaseType caseType = new CaseType();

        caseType.setId( categoryExtRequest.getId() );
        caseType.setCategory( categoryRequestToCategory( categoryExtRequest.getCategory() ) );
        caseType.setName( categoryExtRequest.getName() );

        return caseType;
    }

    @Override
    public CaseTypeResponse toCaseTypeResponse(CaseType category) {
        if ( category == null ) {
            return null;
        }

        CaseTypeResponse caseTypeResponse = new CaseTypeResponse();

        caseTypeResponse.setId( category.getId() );
        caseTypeResponse.setName( category.getName() );

        return caseTypeResponse;
    }

    @Override
    public CaseTypeExtResponse toCaseTypeExtResponse(CaseType category) {
        if ( category == null ) {
            return null;
        }

        CaseTypeExtResponse caseTypeExtResponse = new CaseTypeExtResponse();

        caseTypeExtResponse.setId( category.getId() );
        caseTypeExtResponse.setName( category.getName() );
        caseTypeExtResponse.setCategory( categoryToCategoryResponse( category.getCategory() ) );

        return caseTypeExtResponse;
    }

    protected Category categoryRequestToCategory(CategoryRequest categoryRequest) {
        if ( categoryRequest == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( categoryRequest.getId() );
        category.setName( categoryRequest.getName() );

        return category;
    }

    protected CategoryResponse categoryToCategoryResponse(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryResponse categoryResponse = new CategoryResponse();

        categoryResponse.setId( category.getId() );
        categoryResponse.setName( category.getName() );

        return categoryResponse;
    }
}
