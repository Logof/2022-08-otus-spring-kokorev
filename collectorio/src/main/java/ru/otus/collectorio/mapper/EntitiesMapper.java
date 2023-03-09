package ru.otus.collectorio.mapper;

public interface EntitiesMapper<E, RES, REQ> {
    E toEntity(REQ source);

    RES toResponse(E source);
}
