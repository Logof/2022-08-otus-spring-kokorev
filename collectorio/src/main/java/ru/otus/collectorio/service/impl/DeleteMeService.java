package ru.otus.collectorio.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.collectorio.entity.InfoCard;
import ru.otus.collectorio.mapper.EntitiesMapper;
import ru.otus.collectorio.payload.request.item.InfoCardExtRequest;
import ru.otus.collectorio.payload.response.infoCard.InfoCardExtResponse;
import ru.otus.collectorio.repository.InfoCardRepository;

@Service
public class DeleteMeService extends AbstractEntityServiceImpl<InfoCard, InfoCardExtResponse, InfoCardExtRequest> {

    private final InfoCardRepository repository;
    public DeleteMeService(EntitiesMapper mapper, InfoCardRepository repository) {
        super(mapper, repository);
        this.repository = repository;
    }

    @Override
    InfoCard mergeEntities(InfoCard target, InfoCard source) {
        return source;
    }


}
