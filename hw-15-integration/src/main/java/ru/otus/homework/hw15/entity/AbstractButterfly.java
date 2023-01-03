package ru.otus.homework.hw15.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public abstract class AbstractButterfly {
    private final UUID id;
    private int age;
    private int size;
    private boolean isAlive;

    public AbstractButterfly(UUID id, int age, int size) {
        this.id = id;
        this.age = age;
        this.size = size;
        this.isAlive = true;
    }

}
