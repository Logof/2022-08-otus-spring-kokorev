package ru.otus.collectorio.service.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.collectorio.entity.BaseEntity;
import ru.otus.collectorio.mapper.EntitiesMapper;

import java.util.Objects;

public abstract class AbstractEntityServiceImpl<E extends BaseEntity, RES, REQ> {

    private final EntitiesMapper mapper;

    private final JpaRepository<E, Long> repository;


    public AbstractEntityServiceImpl(EntitiesMapper mapper,
                                     JpaRepository<E, Long> repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public RES save(REQ inputObject) {
        E savedObject;
        E inputEntity = (E) mapper.toEntity(inputObject);

        if (Objects.isNull(inputEntity.getId())) {
            savedObject = repository.save(inputEntity);
        } else {
            Class<? extends E> impl;

            E foundEntity = repository.findById(inputEntity.getId()).orElse( (E)null );
            savedObject = mergeEntities(foundEntity, inputEntity);
            savedObject = repository.save(savedObject);
        }

        return (RES) mapper.toResponse(savedObject);
    }

    abstract E mergeEntities(E target, E source);
}