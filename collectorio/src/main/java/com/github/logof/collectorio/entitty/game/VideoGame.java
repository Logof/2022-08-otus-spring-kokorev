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
@Table(name = "video_game")
public class VideoGame {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = UUIDGenerator.UUID_GEN_STRATEGY)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "name_alternative")
    private String alternativeName;

    @ManyToMany
    private List<Platform> platformList;
}
