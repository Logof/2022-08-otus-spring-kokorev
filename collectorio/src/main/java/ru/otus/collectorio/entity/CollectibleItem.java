package ru.otus.collectorio.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "collectible_items")
public class CollectibleItem {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    //TODO изменить на enum
    private String condition;

    //TODO изменить на кодирование/объект в зависимости от категории
    private String equipment;

    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany
    @JoinTable(name = "collectible_assoc",
            joinColumns = { @JoinColumn(name = "collectible_id") },
            inverseJoinColumns = { @JoinColumn(name = "card_id")}
    )
    private List<ItemCard> itemCards;

    private String description;

    private String boxArtFront;

    private String boxArtBack;

    private String physicalMediaArt;
}
