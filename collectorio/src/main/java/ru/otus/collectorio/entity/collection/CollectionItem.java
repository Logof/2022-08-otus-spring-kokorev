package ru.otus.collectorio.entity.collection;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "collection_items")
public class CollectionItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(name = "collection_items_assoc", joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items;

}
