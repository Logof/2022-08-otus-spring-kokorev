package com.github.logof.collectorio.mapper.game;

import com.github.logof.collectorio.entitty.game.CollectableGame;
import com.github.logof.collectorio.entitty.game.dto.CollectableGameDto;
import com.github.logof.collectorio.mapper.DtoToEntityMapper;
import com.github.logof.collectorio.mapper.EntityToDtoMapper;
import org.mapstruct.Mapper;

@Mapper
public interface CollectableGameMapper extends DtoToEntityMapper<CollectableGameDto, CollectableGame>,
        EntityToDtoMapper<CollectableGame, CollectableGameDto> {

    //@Mapping(target = "compilation", expression = "java(source.getVideoGameList().size() > 1)")
    //TODO подумать как использовать раздельно альтернативное и обычное название
    /*@Mapping(target = "entityName", expression = "java(source.getVideoGameList().size() > 1 " +
            "? source.getEntityName() " +
            ": source.getVideoGameList().get(0).getName())")
    CollectableGameDto toDto(CollectableGame source);*/
}
