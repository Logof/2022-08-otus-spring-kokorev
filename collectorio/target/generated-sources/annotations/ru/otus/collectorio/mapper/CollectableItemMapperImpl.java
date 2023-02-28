package ru.otus.collectorio.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.otus.collectorio.entity.CaseType;
import ru.otus.collectorio.entity.Category;
import ru.otus.collectorio.entity.CollectibleItem;
import ru.otus.collectorio.entity.InfoCard;
import ru.otus.collectorio.payload.request.caseType.CaseTypeRequest;
import ru.otus.collectorio.payload.request.category.CategoryRequest;
import ru.otus.collectorio.payload.request.collectible.CollectibleItemRequest;
import ru.otus.collectorio.payload.request.infoCard.InfoCardRequest;
import ru.otus.collectorio.payload.response.caseType.CaseTypeResponse;
import ru.otus.collectorio.payload.response.category.CategoryResponse;
import ru.otus.collectorio.payload.response.collectableItem.CollectibleItemExtResponse;
import ru.otus.collectorio.payload.response.collectableItem.CollectibleItemResponse;
import ru.otus.collectorio.payload.response.infoCard.InfoCardExtResponse;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-28T12:24:28+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.18 (Oracle Corporation)"
)
@Component
public class CollectableItemMapperImpl implements CollectableItemMapper {

    @Override
    public CollectibleItem toCollectibleItem(CollectibleItemRequest dto) {
        if ( dto == null ) {
            return null;
        }

        CollectibleItem collectibleItem = new CollectibleItem();

        collectibleItem.setId( dto.getId() );
        collectibleItem.setName( dto.getName() );
        collectibleItem.setCondition( dto.getCondition() );
        collectibleItem.setEquipment( dto.getEquipment() );
        collectibleItem.setCaseType( caseTypeRequestToCaseType( dto.getCaseType() ) );
        collectibleItem.setCategory( categoryRequestToCategory( dto.getCategory() ) );
        collectibleItem.setInfoCards( infoCardRequestListToInfoCardList( dto.getInfoCards() ) );

        return collectibleItem;
    }

    @Override
    public CollectibleItemResponse toCollectibleItemResponse(CollectibleItem collectibleItem) {
        if ( collectibleItem == null ) {
            return null;
        }

        CollectibleItemResponse collectibleItemResponse = new CollectibleItemResponse();

        collectibleItemResponse.setId( collectibleItem.getId() );
        collectibleItemResponse.setName( collectibleItem.getName() );
        collectibleItemResponse.setCondition( collectibleItem.getCondition() );
        collectibleItemResponse.setEquipment( collectibleItem.getEquipment() );
        collectibleItemResponse.setCategory( categoryToCategoryResponse( collectibleItem.getCategory() ) );
        collectibleItemResponse.setInfoCards( infoCardListToInfoCardExtResponseList( collectibleItem.getInfoCards() ) );
        collectibleItemResponse.setCaseType( caseTypeToCaseTypeResponse( collectibleItem.getCaseType() ) );

        return collectibleItemResponse;
    }

    @Override
    public CollectibleItemExtResponse toCollectibleItemExtResponse(CollectibleItem collectibleItem) {
        if ( collectibleItem == null ) {
            return null;
        }

        CollectibleItemExtResponse collectibleItemExtResponse = new CollectibleItemExtResponse();

        collectibleItemExtResponse.setId( collectibleItem.getId() );
        collectibleItemExtResponse.setName( collectibleItem.getName() );
        collectibleItemExtResponse.setCondition( collectibleItem.getCondition() );
        collectibleItemExtResponse.setEquipment( collectibleItem.getEquipment() );
        collectibleItemExtResponse.setCategory( categoryToCategoryResponse( collectibleItem.getCategory() ) );
        collectibleItemExtResponse.setInfoCards( infoCardListToInfoCardExtResponseList( collectibleItem.getInfoCards() ) );
        collectibleItemExtResponse.setCaseType( caseTypeToCaseTypeResponse( collectibleItem.getCaseType() ) );
        collectibleItemExtResponse.setDescription( collectibleItem.getDescription() );
        collectibleItemExtResponse.setBoxArtFront( collectibleItem.getBoxArtFront() );
        collectibleItemExtResponse.setBoxArtBack( collectibleItem.getBoxArtBack() );
        collectibleItemExtResponse.setPhysicalMediaArt( collectibleItem.getPhysicalMediaArt() );

        return collectibleItemExtResponse;
    }

    protected CaseType caseTypeRequestToCaseType(CaseTypeRequest caseTypeRequest) {
        if ( caseTypeRequest == null ) {
            return null;
        }

        CaseType caseType = new CaseType();

        caseType.setId( caseTypeRequest.getId() );
        caseType.setName( caseTypeRequest.getName() );

        return caseType;
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

    protected InfoCard infoCardRequestToInfoCard(InfoCardRequest infoCardRequest) {
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

    protected List<InfoCard> infoCardRequestListToInfoCardList(List<InfoCardRequest> list) {
        if ( list == null ) {
            return null;
        }

        List<InfoCard> list1 = new ArrayList<InfoCard>( list.size() );
        for ( InfoCardRequest infoCardRequest : list ) {
            list1.add( infoCardRequestToInfoCard( infoCardRequest ) );
        }

        return list1;
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

    protected InfoCardExtResponse infoCardToInfoCardExtResponse(InfoCard infoCard) {
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

    protected List<InfoCardExtResponse> infoCardListToInfoCardExtResponseList(List<InfoCard> list) {
        if ( list == null ) {
            return null;
        }

        List<InfoCardExtResponse> list1 = new ArrayList<InfoCardExtResponse>( list.size() );
        for ( InfoCard infoCard : list ) {
            list1.add( infoCardToInfoCardExtResponse( infoCard ) );
        }

        return list1;
    }

    protected CaseTypeResponse caseTypeToCaseTypeResponse(CaseType caseType) {
        if ( caseType == null ) {
            return null;
        }

        CaseTypeResponse caseTypeResponse = new CaseTypeResponse();

        caseTypeResponse.setId( caseType.getId() );
        caseTypeResponse.setName( caseType.getName() );

        return caseTypeResponse;
    }
}
