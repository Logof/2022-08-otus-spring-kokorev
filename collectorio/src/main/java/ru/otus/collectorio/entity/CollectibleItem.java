package ru.otus.collectorio.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "collectible_items")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class CollectibleItem implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    //TODO изменить на enum
    @Column(name = "condition")
    private String condition;

    //TODO изменить на кодирование/объект в зависимости от категории
    @Column(name = "equipment")
    private String equipment;

    @OneToOne
    @JoinColumn(name = "case_type_id")
    private CaseType caseType;

    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany
    @JoinTable(name = "collectible_assoc",
            joinColumns = { @JoinColumn(name = "collectible_id") },
            inverseJoinColumns = { @JoinColumn(name = "card_id")}
    )
    private List<InfoCard> infoCards;

    @Column(name = "description")
    private String description;

    @Column(name = "box_art_front")
    private String boxArtFront;

    @Column(name = "box_art_back")
    private String boxArtBack;

    @Column(name = "physical_media_art")
    private String physicalMediaArt;

    @Column(name = "user_creator", nullable = false)
    private String creator;

    @OneToOne
    @JoinColumn(name = "collection_id")
    private Collection collection;

}
