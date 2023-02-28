package ru.otus.collectorio.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.otus.collectorio.entity.Category;
import ru.otus.collectorio.entity.InfoCard;
import ru.otus.collectorio.payload.request.category.CategoryRequest;
import ru.otus.collectorio.payload.request.infoCard.InfoCardExtRequest;
import ru.otus.collectorio.payload.response.category.CategoryResponse;
import ru.otus.collectorio.payload.response.infoCard.InfoCardExtResponse;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-28T00:53:29+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.18 (Oracle Corporation)"
)
@Component
public class DeleteMeMapperImpl implements DeleteMeMapper {

    @Override
    public InfoCard toEntity(InfoCardExtRequest source) {
        if ( source == null ) {
            return null;
        }

        InfoCard infoCard = new InfoCard();

        infoCard.setId( source.getId() );
        infoCard.setName( source.getName() );
        infoCard.setNameAlt( source.getNameAlt() );
        infoCard.setReleaseType( source.getReleaseType() );
        infoCard.setReleaseDate( source.getReleaseDate() );
        infoCard.setReleaseRegion( source.getReleaseRegion() );
        infoCard.setPublisher( source.getPublisher() );
        infoCard.setDeveloper( source.getDeveloper() );
        infoCard.setCategory( categoryRequestToCategory( source.getCategory() ) );
        infoCard.setGenre( source.getGenre() );
        infoCard.setRating( source.getRating() );
        infoCard.setBoxText( source.getBoxText() );
        infoCard.setDescription( source.getDescription() );

        return infoCard;
    }

    @Override
    public InfoCardExtResponse toResponse(InfoCard source) {
        if ( source == null ) {
            return null;
        }

        InfoCardExtResponse infoCardExtResponse = new InfoCardExtResponse();

        infoCardExtResponse.setId( source.getId() );
        infoCardExtResponse.setName( source.getName() );
        infoCardExtResponse.setNameAlt( source.getNameAlt() );
        infoCardExtResponse.setCategory( categoryToCategoryResponse( source.getCategory() ) );
        infoCardExtResponse.setReleaseType( source.getReleaseType() );
        infoCardExtResponse.setDescription( source.getDescription() );
        infoCardExtResponse.setReleaseDate( source.getReleaseDate() );
        infoCardExtResponse.setReleaseRegion( source.getReleaseRegion() );
        infoCardExtResponse.setPublisher( source.getPublisher() );
        infoCardExtResponse.setDeveloper( source.getDeveloper() );
        infoCardExtResponse.setGenre( source.getGenre() );
        infoCardExtResponse.setRating( source.getRating() );
        infoCardExtResponse.setBoxText( source.getBoxText() );

        return infoCardExtResponse;
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
