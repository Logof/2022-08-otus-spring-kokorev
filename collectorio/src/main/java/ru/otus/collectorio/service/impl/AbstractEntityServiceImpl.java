package ru.otus.collectorio.service.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.collectorio.entity.BaseEntity;
import ru.otus.collectorio.mapper.EntitiesMapper;
import ru.otus.collectorio.payload.request.Request;
import ru.otus.collectorio.payload.response.Response;

import java.util.Objects;

public abstract class AbstractEntityServiceImpl<E extends BaseEntity> {

    private final EntitiesMapper mapper;

    private final JpaRepository<E, Long> repository;

    public AbstractEntityServiceImpl(EntitiesMapper mapper,
                                     JpaRepository<E, Long> repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public Response save(Request inputRequest) {
        E savedObject;
        E inputEntity = (E) mapper.toEntity(inputRequest);

        if (Objects.isNull(inputEntity.getId())) {
            savedObject = repository.save(inputEntity);
        } else {
            Class<? extends E> impl;
            E foundEntity = repository.findById(inputEntity.getId()).orElse( (E)null );
            savedObject = mergeEntities(foundEntity, inputEntity);
            savedObject = repository.save(savedObject);
        }

        return (Response)mapper.toResponse(savedObject);
    }

    protected abstract E mergeEntities(E target, E source);
}