package ru.otus.collectorio.service.infoCard;

import org.springframework.transaction.annotation.Transactional;
import ru.otus.collectorio.entity.InfoCard;
import ru.otus.collectorio.exception.DataNotFoundException;
import ru.otus.collectorio.mapper.InfoCardMapper;
import ru.otus.collectorio.payload.request.infoCard.InfoCardExtRequest;
import ru.otus.collectorio.payload.response.infoCard.InfoCardExtResponse;
import ru.otus.collectorio.repository.InfoCardRepository;
import ru.otus.collectorio.service.impl.AbstractEntityServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class InfoCardExtServiceImpl extends AbstractEntityServiceImpl<InfoCard>
        implements InfoCardService<InfoCardExtRequest, InfoCardExtResponse, Long> {

    private final InfoCardRepository infoCardRepository;

    private final InfoCardMapper mapper;

    public InfoCardExtServiceImpl(InfoCardMapper mapper, InfoCardRepository infoCardRepository) {
        super(mapper, infoCardRepository);
        this.infoCardRepository = infoCardRepository;
        this.mapper = mapper;
    }

    @Override
    protected InfoCard mergeEntities(InfoCard target, InfoCard source) {
        return null;
    }

    @Override
    public List<InfoCardExtResponse> getAll() {
        return new ArrayList<>();
    }

    @Override
    @Transactional(readOnly = true)
    public InfoCardExtResponse findById(Long id) {
        return mapper.toInfoCardExtResponse(infoCardRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException()));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        infoCardRepository.deleteById(id);
    }
}
