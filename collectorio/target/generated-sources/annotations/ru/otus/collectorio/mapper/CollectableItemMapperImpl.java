package ru.otus.collectorio.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.collectorio.entity.CaseType;
import ru.otus.collectorio.entity.InfoCard;
import ru.otus.collectorio.entity.CollectibleItem;
import ru.otus.collectorio.payload.request.caseType.CaseTypeRequest;
import ru.otus.collectorio.payload.request.collectible.CollectibleItemRequest;
import ru.otus.collectorio.payload.request.item.InfoCardWithoutCategoryRequest;
import ru.otus.collectorio.payload.response.caseType.CaseTypeResponse;
import ru.otus.collectorio.payload.response.collectableItem.CollectibleItemResponse;
import ru.otus.collectorio.payload.response.item.InfoCardWithoutCategoryResponse;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-27T16:39:39+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.18 (Oracle Corporation)"
)
@Component
public class CollectableItemMapperImpl implements CollectableItemMapper {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public CollectibleItem toCollectibleItem(CollectibleItemRequest dto) {
        if ( dto == null ) {
            return null;
        }

        CollectibleItem collectibleItem = new CollectibleItem();

        collectibleItem.setName( dto.getName() );
        collectibleItem.setCondition( dto.getCondition() );
        collectibleItem.setEquipment( dto.getEquipment() );
        collectibleItem.setCaseType( caseTypeRequestToCaseType( dto.getCaseType() ) );
        collectibleItem.setCategory( categoryMapper.toCategory( dto.getCategory() ) );
        collectibleItem.setInfoCards( infoCardWithoutCategoryRequestListToInfoCardList( dto.getInfoCards() ) );

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
        collectibleItemResponse.setInfoCards( infoCardListToInfoCardWithoutCategoryResponseList( collectibleItem.getInfoCards() ) );
        collectibleItemResponse.setCaseType( caseTypeToCaseTypeResponse( collectibleItem.getCaseType() ) );

        handleAccounts( collectibleItem, collectibleItemResponse );

        return collectibleItemResponse;
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

    protected InfoCardWithoutCategoryResponse infoCardToInfoCardWithoutCategoryResponse(InfoCard infoCard) {
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

    protected List<InfoCardWithoutCategoryResponse> infoCardListToInfoCardWithoutCategoryResponseList(List<InfoCard> list) {
        if ( list == null ) {
            return null;
        }

        List<InfoCardWithoutCategoryResponse> list1 = new ArrayList<InfoCardWithoutCategoryResponse>( list.size() );
        for ( InfoCard infoCard : list ) {
            list1.add( infoCardToInfoCardWithoutCategoryResponse( infoCard ) );
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
