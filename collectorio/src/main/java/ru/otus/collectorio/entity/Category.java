package ru.otus.collectorio.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "categories")
@NoArgsConstructor
@AllArgsConstructor
@NamedEntityGraph(name = "category-entity-graph",
        attributeNodes = { @NamedAttributeNode(value = "parent") }
)
public class Category implements BaseEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = { CascadeType.MERGE })
    @JoinColumn(name="parent_id")
    private Category parent;

}
