package com.github.logof.collectorio.entitty.game;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.id.UUIDGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "collectable_game")
public class CollectableGame {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = UUIDGenerator.UUID_GEN_STRATEGY)
    @Column(name = "id")
    private UUID id;

    @OneToMany
    private List<VideoGame> videoGameList;

    private String entityName;

    @ManyToOne
    private Platform platform;

    @ManyToOne
    private MediaType mediaType;

    @Column(name = "description")
    private String description;
}
