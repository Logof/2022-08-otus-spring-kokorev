package ru.otus.collectorio.payload.response.collectableItem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CollectibleItemExtResponse extends CollectibleItemResponse {

    private String description;

    private String boxArtFront;

    private String boxArtBack;

    private String physicalMediaArt;
}
