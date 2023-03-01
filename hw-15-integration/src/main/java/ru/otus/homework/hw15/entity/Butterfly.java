package ru.otus.homework.hw15.entity;

import java.util.UUID;

public class Butterfly extends AbstractButterfly {
    public Butterfly(UUID id, int age, int size) {
        super(id, age, size);
    }

    @Override
    public String toString() {
        return "Butterfly {" +
            "id=" + getId() +
            ", age=" + getAge() +
            ", size=" + getSize() +
            ", alive=" + isAlive() +
            "}";
    }
}
