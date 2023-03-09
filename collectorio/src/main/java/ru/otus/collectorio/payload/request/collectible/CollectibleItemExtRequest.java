package ru.otus.collectorio.payload.request.collectible;

import lombok.Getter;
import lombok.Setter;
import ru.otus.collectorio.payload.request.Request;

@Getter
@Setter
public class CollectibleItemExtRequest extends CollectibleItemRequest implements Request {

    private String description;

    private String boxArtFront;

    private String boxArtBack;

    private String physicalMediaArt;
}
