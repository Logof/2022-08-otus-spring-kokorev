package com.github.logof.collectorio.mapper.game;

import com.github.logof.collectorio.entitty.game.CollectableGame;
import com.github.logof.collectorio.entitty.game.VideoGame;
import com.github.logof.collectorio.entitty.game.dto.CollectableGameDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-22T02:29:34+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.17 (Oracle Corporation)"
)
@Component
public class CollectableGameMapperImpl implements CollectableGameMapper {

    @Override
    public CollectableGameDto toDto(CollectableGame entity) {
        if ( entity == null ) {
            return null;
        }

        CollectableGameDto collectableGameDto = new CollectableGameDto();

        collectableGameDto.setId( entity.getId() );
        List<VideoGame> list = entity.getVideoGameList();
        if ( list != null ) {
            collectableGameDto.setVideoGameList( new ArrayList<VideoGame>( list ) );
        }
        collectableGameDto.setEntityName( entity.getEntityName() );
        collectableGameDto.setPlatform( entity.getPlatform() );
        collectableGameDto.setMediaType( entity.getMediaType() );
        collectableGameDto.setDescription( entity.getDescription() );

        return collectableGameDto;
    }

    @Override
    public CollectableGame toEntity(CollectableGameDto dto) {
        if ( dto == null ) {
            return null;
        }

        CollectableGame collectableGame = new CollectableGame();

        collectableGame.setId( dto.getId() );
        List<VideoGame> list = dto.getVideoGameList();
        if ( list != null ) {
            collectableGame.setVideoGameList( new ArrayList<VideoGame>( list ) );
        }
        collectableGame.setEntityName( dto.getEntityName() );
        collectableGame.setPlatform( dto.getPlatform() );
        collectableGame.setMediaType( dto.getMediaType() );
        collectableGame.setDescription( dto.getDescription() );

        return collectableGame;
    }
}
