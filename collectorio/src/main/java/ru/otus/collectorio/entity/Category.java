package ru.otus.collectorio.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "categories")
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Getter(value = AccessLevel.NONE)
    @Setter(value = AccessLevel.NONE)
    @Column(name = "parent_id")
    private Long parentId;

    @OneToMany
    @JoinColumn(name="parent_id")
    private List<Category> children;

    public boolean isChildren() {
        return Objects.nonNull(parentId);
    }
}
