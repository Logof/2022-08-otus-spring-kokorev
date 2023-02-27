package ru.otus.collectorio.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.collectorio.entity.Category;
import ru.otus.collectorio.entity.InfoCard;
import ru.otus.collectorio.exception.DataNotFoundException;
import ru.otus.collectorio.mapper.ItemCardMapper;
import ru.otus.collectorio.payload.response.item.InfoCardWithoutCategoryResponse;
import ru.otus.collectorio.repository.ItemCardRepository;
import ru.otus.collectorio.service.InfoCardService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InfoCardServiceImpl implements InfoCardService {

    private final ItemCardRepository itemCardRepository;

    private final ItemCardMapper mapper;

    public InfoCardServiceImpl(ItemCardRepository itemCardRepository, ItemCardMapper mapper) {
        this.itemCardRepository = itemCardRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<InfoCard> getAllInCategoryWithCategory(Long id) {
        return itemCardRepository.findAllByCategory_Id(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<InfoCardWithoutCategoryResponse> getAllInCategory(Long id) {
        return itemCardRepository.findAllByCategory_Id(id)
                .stream().map(item -> mapper.toWithoutCategoryResponse(item))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public InfoCard findById(Long id) {
        return itemCardRepository.findById(id).orElseThrow(() -> new DataNotFoundException());
    }

    @Override
    @Transactional
    public InfoCard save(InfoCard infoCard) {
        return itemCardRepository.save(infoCard);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        itemCardRepository.deleteById(id);
    }

    @Override
    public List<InfoCard> findAllByQuery(String name, Category category) {
        return null;
    }
}
