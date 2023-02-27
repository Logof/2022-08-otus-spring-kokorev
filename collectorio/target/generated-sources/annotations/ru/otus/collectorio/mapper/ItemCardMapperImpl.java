package ru.otus.collectorio.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.otus.collectorio.entity.InfoCard;
import ru.otus.collectorio.payload.response.item.InfoCardWithoutCategoryResponse;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-27T16:39:39+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.18 (Oracle Corporation)"
)
@Component
public class ItemCardMapperImpl implements ItemCardMapper {

    @Override
    public InfoCardWithoutCategoryResponse toWithoutCategoryResponse(InfoCard infoCard) {
        if ( infoCard == null ) {
            return null;
        }

        InfoCardWithoutCategoryResponse infoCardWithoutCategoryResponse = new InfoCardWithoutCategoryResponse();

        infoCardWithoutCategoryResponse.setId( infoCard.getId() );
        infoCardWithoutCategoryResponse.setName( infoCard.getName() );
        infoCardWithoutCategoryResponse.setNameAlt( infoCard.getNameAlt() );
        infoCardWithoutCategoryResponse.setReleaseType( infoCard.getReleaseType() );
        infoCardWithoutCategoryResponse.setReleaseDate( infoCard.getReleaseDate() );
        infoCardWithoutCategoryResponse.setReleaseRegion( infoCard.getReleaseRegion() );
        infoCardWithoutCategoryResponse.setPublisher( infoCard.getPublisher() );
        infoCardWithoutCategoryResponse.setDeveloper( infoCard.getDeveloper() );
        infoCardWithoutCategoryResponse.setGenre( infoCard.getGenre() );
        infoCardWithoutCategoryResponse.setRating( infoCard.getRating() );
        infoCardWithoutCategoryResponse.setBoxText( infoCard.getBoxText() );
        infoCardWithoutCategoryResponse.setDescription( infoCard.getDescription() );

        return infoCardWithoutCategoryResponse;
    }
}
