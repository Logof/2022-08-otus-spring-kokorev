package ru.otus.collectorio.payload.request.collectible;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CollectibleItemExtRequest extends CollectibleItemRequest {

    private String description;

    private String boxArtFront;

    private String boxArtBack;

    private String physicalMediaArt;
}
