package ru.otus.collectorio.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "collections")
public class Collection {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany
    @JoinTable(name = "collection_assoc",
            joinColumns = { @JoinColumn(name = "collection_id") },
            inverseJoinColumns = { @JoinColumn(name = "collectable_id")}
    )
    private List<CollectibleItem> collectibleItemList;
}
