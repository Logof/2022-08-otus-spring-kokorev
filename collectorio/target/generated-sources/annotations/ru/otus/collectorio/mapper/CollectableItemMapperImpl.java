package ru.otus.collectorio.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.otus.collectorio.entity.CaseType;
import ru.otus.collectorio.entity.CollectibleItem;
import ru.otus.collectorio.entity.InfoCard;
import ru.otus.collectorio.payload.request.caseType.CaseTypeWithoutCategoryRequest;
import ru.otus.collectorio.payload.request.collectible.CollectibleItemRequest;
import ru.otus.collectorio.payload.request.item.InfoCardWithoutCategoryRequest;
import ru.otus.collectorio.payload.response.caseType.CaseTypeWithoutCategoryResponse;
import ru.otus.collectorio.payload.response.collectableItem.CollectibleItemResponse;
import ru.otus.collectorio.payload.response.item.ItemCardWithoutCategoryResponse;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-17T00:20:01+0500",
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

        collectibleItem.setName( dto.getName() );
        collectibleItem.setCondition( dto.getCondition() );
        collectibleItem.setEquipment( dto.getEquipment() );
        collectibleItem.setCategory( dto.getCategory() );
        collectibleItem.setInfoCards( infoCardWithoutCategoryRequestListToInfoCardList( dto.getInfoCards() ) );
        collectibleItem.setDescription( dto.getDescription() );
        collectibleItem.setBoxArtFront( dto.getBoxArtFront() );
        collectibleItem.setBoxArtBack( dto.getBoxArtBack() );
        collectibleItem.setPhysicalMediaArt( dto.getPhysicalMediaArt() );
        collectibleItem.setCaseType( caseTypeWithoutCategoryRequestToCaseType( dto.getCaseType() ) );

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
        collectibleItemResponse.setCategory( collectibleItem.getCategory() );
        collectibleItemResponse.setInfoCards( infoCardListToItemCardWithoutCategoryResponseList( collectibleItem.getInfoCards() ) );
        collectibleItemResponse.setDescription( collectibleItem.getDescription() );
        collectibleItemResponse.setBoxArtFront( collectibleItem.getBoxArtFront() );
        collectibleItemResponse.setBoxArtBack( collectibleItem.getBoxArtBack() );
        collectibleItemResponse.setPhysicalMediaArt( collectibleItem.getPhysicalMediaArt() );
        collectibleItemResponse.setCaseType( caseTypeToCaseTypeWithoutCategoryResponse( collectibleItem.getCaseType() ) );

        return collectibleItemResponse;
    }

    protected InfoCard infoCardWithoutCategoryRequestToInfoCard(InfoCardWithoutCategoryRequest infoCardWithoutCategoryRequest) {
        if ( infoCardWithoutCategoryRequest == null ) {
            return null;
        }

        InfoCard infoCard = new InfoCard();

        infoCard.setName( infoCardWithoutCategoryRequest.getName() );
        infoCard.setNameAlt( infoCardWithoutCategoryRequest.getNameAlt() );
        infoCard.setReleaseType( infoCardWithoutCategoryRequest.getReleaseType() );
        infoCard.setReleaseDate( infoCardWithoutCategoryRequest.getReleaseDate() );
        infoCard.setReleaseRegion( infoCardWithoutCategoryRequest.getReleaseRegion() );
        infoCard.setPublisher( infoCardWithoutCategoryRequest.getPublisher() );
        infoCard.setDeveloper( infoCardWithoutCategoryRequest.getDeveloper() );
        infoCard.setGenre( infoCardWithoutCategoryRequest.getGenre() );
        infoCard.setRating( infoCardWithoutCategoryRequest.getRating() );
        infoCard.setBoxText( infoCardWithoutCategoryRequest.getBoxText() );
        infoCard.setDescription( infoCardWithoutCategoryRequest.getDescription() );

        return infoCard;
    }

    protected List<InfoCard> infoCardWithoutCategoryRequestListToInfoCardList(List<InfoCardWithoutCategoryRequest> list) {
        if ( list == null ) {
            return null;
        }

        List<InfoCard> list1 = new ArrayList<InfoCard>( list.size() );
        for ( InfoCardWithoutCategoryRequest infoCardWithoutCategoryRequest : list ) {
            list1.add( infoCardWithoutCategoryRequestToInfoCard( infoCardWithoutCategoryRequest ) );
        }

        return list1;
    }

    protected CaseType caseTypeWithoutCategoryRequestToCaseType(CaseTypeWithoutCategoryRequest caseTypeWithoutCategoryRequest) {
        if ( caseTypeWithoutCategoryRequest == null ) {
            return null;
        }

        CaseType caseType = new CaseType();

        caseType.setId( caseTypeWithoutCategoryRequest.getId() );
        caseType.setCategory( caseTypeWithoutCategoryRequest.getCategory() );
        caseType.setName( caseTypeWithoutCategoryRequest.getName() );

        return caseType;
    }

    protected ItemCardWithoutCategoryResponse infoCardToItemCardWithoutCategoryResponse(InfoCard infoCard) {
        if ( infoCard == null ) {
            return null;
        }

        ItemCardWithoutCategoryResponse itemCardWithoutCategoryResponse = new ItemCardWithoutCategoryResponse();

        itemCardWithoutCategoryResponse.setId( infoCard.getId() );
        itemCardWithoutCategoryResponse.setName( infoCard.getName() );
        itemCardWithoutCategoryResponse.setNameAlt( infoCard.getNameAlt() );
        itemCardWithoutCategoryResponse.setReleaseType( infoCard.getReleaseType() );
        itemCardWithoutCategoryResponse.setReleaseDate( infoCard.getReleaseDate() );
        itemCardWithoutCategoryResponse.setReleaseRegion( infoCard.getReleaseRegion() );
        itemCardWithoutCategoryResponse.setPublisher( infoCard.getPublisher() );
        itemCardWithoutCategoryResponse.setDeveloper( infoCard.getDeveloper() );
        itemCardWithoutCategoryResponse.setGenre( infoCard.getGenre() );
        itemCardWithoutCategoryResponse.setRating( infoCard.getRating() );
        itemCardWithoutCategoryResponse.setBoxText( infoCard.getBoxText() );
        itemCardWithoutCategoryResponse.setDescription( infoCard.getDescription() );

        return itemCardWithoutCategoryResponse;
    }

    protected List<ItemCardWithoutCategoryResponse> infoCardListToItemCardWithoutCategoryResponseList(List<InfoCard> list) {
        if ( list == null ) {
            return null;
        }

        List<ItemCardWithoutCategoryResponse> list1 = new ArrayList<ItemCardWithoutCategoryResponse>( list.size() );
        for ( InfoCard infoCard : list ) {
            list1.add( infoCardToItemCardWithoutCategoryResponse( infoCard ) );
        }

        return list1;
    }

    protected CaseTypeWithoutCategoryResponse caseTypeToCaseTypeWithoutCategoryResponse(CaseType caseType) {
        if ( caseType == null ) {
            return null;
        }

        CaseTypeWithoutCategoryResponse caseTypeWithoutCategoryResponse = new CaseTypeWithoutCategoryResponse();

        caseTypeWithoutCategoryResponse.setId( caseType.getId() );
        caseTypeWithoutCategoryResponse.setName( caseType.getName() );

        return caseTypeWithoutCategoryResponse;
    }
}
