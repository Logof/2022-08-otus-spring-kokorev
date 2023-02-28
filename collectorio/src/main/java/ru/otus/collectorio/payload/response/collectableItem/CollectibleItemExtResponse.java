package ru.otus.collectorio.payload.response.collectableItem;

import lombok.Getter;
import lombok.Setter;
import ru.otus.collectorio.payload.response.Response;

@Getter
@Setter
public class CollectibleItemExtResponse extends CollectibleItemResponse implements Response {

    private String description;

    private String boxArtFront;

    private String boxArtBack;

    private String physicalMediaArt;
}
