package ru.otus.collectorio.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.collectorio.entity.Category;
import ru.otus.collectorio.entity.ItemCard;
import ru.otus.collectorio.exception.DataNotFoundException;
import ru.otus.collectorio.repository.ItemCardRepository;
import ru.otus.collectorio.service.ItemCardService;

import java.util.List;

@Service
public class ItemCardServiceImpl implements ItemCardService {

    private final ItemCardRepository itemCardRepository;

    public ItemCardServiceImpl(ItemCardRepository itemCardRepository) {
        this.itemCardRepository = itemCardRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItemCard> getAllInCategory(Long id) {
        return itemCardRepository.findAllByCategory_Id(id);
    }

    @Override
    @Transactional(readOnly = true)
    public ItemCard findById(Long id) {
        return itemCardRepository.findById(id).orElseThrow(() -> new DataNotFoundException());
    }

    @Override
    @Transactional
    public ItemCard save(ItemCard itemCard) {
        return itemCardRepository.save(itemCard);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        itemCardRepository.deleteById(id);
    }

    @Override
    public List<ItemCard> findAllByQuery(String name, Category category) {
        return null;
    }
}
