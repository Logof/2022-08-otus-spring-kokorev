package com.github.logof.collectorio.entitty.game;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "media_type")
public class MediaType {
    @Id
    @Column(name = "code", unique=true, nullable = false)
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    private Platform platform;
}
