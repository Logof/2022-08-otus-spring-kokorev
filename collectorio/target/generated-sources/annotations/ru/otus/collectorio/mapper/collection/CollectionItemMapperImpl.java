package ru.otus.collectorio.mapper.collection;

import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.collectorio.dto.collection.CollectionItemDto;
import ru.otus.collectorio.entity.collection.CollectionItem;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-11T00:45:56+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.17 (Oracle Corporation)"
)
@Component
public class CollectionItemMapperImpl implements CollectionItemMapper {

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public CollectionItemDto toDto(CollectionItem entity) {
        if ( entity == null ) {
            return null;
        }

        CollectionItemDto collectionItemDto = new CollectionItemDto();

        collectionItemDto.setId( entity.getId() );
        collectionItemDto.setName( entity.getName() );
        collectionItemDto.setItems( itemMapper.toDtos( entity.getItems() ) );

        return collectionItemDto;
    }
}
