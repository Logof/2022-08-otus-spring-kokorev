package ru.otus.collectorio.mapper;

import org.mapstruct.Mapper;
import ru.otus.collectorio.entity.InfoCard;
import ru.otus.collectorio.payload.request.item.InfoCardExtRequest;
import ru.otus.collectorio.payload.response.infoCard.InfoCardExtResponse;

@Mapper
public interface DeleteMeMapper extends EntitiesMapper<InfoCard, InfoCardExtResponse, InfoCardExtRequest> {
}
