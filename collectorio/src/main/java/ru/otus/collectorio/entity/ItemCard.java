package ru.otus.collectorio.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "item_cards")
public class ItemCard {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "name_alt")
    private String nameAlt;

    @Column(name = "release_type")
    private String releaseType;

    @Column(name = "release_date")
    private Date releaseDate;

    @Column(name = "release_region")
    private String releaseRegion;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "developer")
    private String developer;

    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "genre")
    private String genre;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "box_text")
    private String boxText;

    @Column(name = "description")
    private String description;

}
