package com.github.logof.collectorio.entitty.game.dto;

import com.github.logof.collectorio.entitty.game.MediaType;
import com.github.logof.collectorio.entitty.game.Platform;
import com.github.logof.collectorio.entitty.game.VideoGame;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CollectableGameDto {
    private UUID id;

    private List<VideoGame> videoGameList;

    private String entityName;

    private Platform platform;

    private MediaType mediaType;

    private String description;
}
