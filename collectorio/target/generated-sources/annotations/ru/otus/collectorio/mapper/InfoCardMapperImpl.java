package ru.otus.collectorio.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.otus.collectorio.entity.Category;
import ru.otus.collectorio.entity.InfoCard;
import ru.otus.collectorio.payload.request.category.CategoryRequest;
import ru.otus.collectorio.payload.request.item.InfoCardExtRequest;
import ru.otus.collectorio.payload.request.item.InfoCardRequest;
import ru.otus.collectorio.payload.response.category.CategoryResponse;
import ru.otus.collectorio.payload.response.infoCard.InfoCardExtResponse;
import ru.otus.collectorio.payload.response.infoCard.InfoCardResponse;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-27T19:01:57+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.18 (Oracle Corporation)"
)
@Component
public class InfoCardMapperImpl implements InfoCardMapper {

    @Override
    public InfoCardResponse toInfoCardResponse(InfoCard infoCard) {
        if ( infoCard == null ) {
            return null;
        }

        InfoCardResponse infoCardResponse = new InfoCardResponse();

        infoCardResponse.setId( infoCard.getId() );
        infoCardResponse.setName( infoCard.getName() );
        infoCardResponse.setNameAlt( infoCard.getNameAlt() );
        infoCardResponse.setCategory( categoryToCategoryResponse( infoCard.getCategory() ) );
        infoCardResponse.setReleaseType( infoCard.getReleaseType() );
        infoCardResponse.setDescription( infoCard.getDescription() );

        return infoCardResponse;
    }

    @Override
    public InfoCardExtResponse toInfoCardExtResponse(InfoCard infoCard) {
        if ( infoCard == null ) {
            return null;
        }

        InfoCardExtResponse infoCardExtResponse = new InfoCardExtResponse();

        infoCardExtResponse.setId( infoCard.getId() );
        infoCardExtResponse.setName( infoCard.getName() );
        infoCardExtResponse.setNameAlt( infoCard.getNameAlt() );
        infoCardExtResponse.setCategory( categoryToCategoryResponse( infoCard.getCategory() ) );
        infoCardExtResponse.setReleaseType( infoCard.getReleaseType() );
        infoCardExtResponse.setDescription( infoCard.getDescription() );
        infoCardExtResponse.setReleaseDate( infoCard.getReleaseDate() );
        infoCardExtResponse.setReleaseRegion( infoCard.getReleaseRegion() );
        infoCardExtResponse.setPublisher( infoCard.getPublisher() );
        infoCardExtResponse.setDeveloper( infoCard.getDeveloper() );
        infoCardExtResponse.setGenre( infoCard.getGenre() );
        infoCardExtResponse.setRating( infoCard.getRating() );
        infoCardExtResponse.setBoxText( infoCard.getBoxText() );

        return infoCardExtResponse;
    }

    @Override
    public InfoCard toInfoCard(InfoCardRequest infoCardRequest) {
        if ( infoCardRequest == null ) {
            return null;
        }

        InfoCard infoCard = new InfoCard();

        infoCard.setId( infoCardRequest.getId() );
        infoCard.setName( infoCardRequest.getName() );
        infoCard.setNameAlt( infoCardRequest.getNameAlt() );
        infoCard.setReleaseType( infoCardRequest.getReleaseType() );
        infoCard.setCategory( categoryRequestToCategory( infoCardRequest.getCategory() ) );
        infoCard.setDescription( infoCardRequest.getDescription() );

        return infoCard;
    }

    @Override
    public InfoCard toInfoCard(InfoCardExtRequest infoCardRequest) {
        if ( infoCardRequest == null ) {
            return null;
        }

        InfoCard infoCard = new InfoCard();

        infoCard.setId( infoCardRequest.getId() );
        infoCard.setName( infoCardRequest.getName() );
        infoCard.setNameAlt( infoCardRequest.getNameAlt() );
        infoCard.setReleaseType( infoCardRequest.getReleaseType() );
        infoCard.setReleaseDate( infoCardRequest.getReleaseDate() );
        infoCard.setReleaseRegion( infoCardRequest.getReleaseRegion() );
        infoCard.setPublisher( infoCardRequest.getPublisher() );
        infoCard.setDeveloper( infoCardRequest.getDeveloper() );
        infoCard.setCategory( categoryRequestToCategory( infoCardRequest.getCategory() ) );
        infoCard.setGenre( infoCardRequest.getGenre() );
        infoCard.setRating( infoCardRequest.getRating() );
        infoCard.setBoxText( infoCardRequest.getBoxText() );
        infoCard.setDescription( infoCardRequest.getDescription() );

        return infoCard;
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

    protected Category categoryRequestToCategory(CategoryRequest categoryRequest) {
        if ( categoryRequest == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( categoryRequest.getId() );
        category.setName( categoryRequest.getName() );

        return category;
    }
}
