package ru.otus.collectorio.entity.collection;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "collections")
public class Collection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToMany
    @JoinTable(name = "collection_assoc", joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "collection_item_id"))
    private List<CollectionItem> item;
}
