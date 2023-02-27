package ru.otus.collectorio.mapper;

import org.mapstruct.Mapper;
import ru.otus.collectorio.entity.InfoCard;
import ru.otus.collectorio.payload.request.item.InfoCardExtRequest;
import ru.otus.collectorio.payload.request.item.InfoCardRequest;
import ru.otus.collectorio.payload.response.infoCard.InfoCardExtResponse;
import ru.otus.collectorio.payload.response.infoCard.InfoCardResponse;

@Mapper
public interface InfoCardMapper {

    InfoCardResponse toInfoCardResponse(InfoCard infoCard);

    InfoCardExtResponse toInfoCardExtResponse(InfoCard infoCard);

    InfoCard toInfoCard(InfoCardRequest infoCardRequest);

    InfoCard toInfoCard(InfoCardExtRequest infoCardRequest);
}
