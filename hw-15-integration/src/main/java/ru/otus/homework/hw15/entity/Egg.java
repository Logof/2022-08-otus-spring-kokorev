package ru.otus.homework.hw15.entity;

import java.util.UUID;

public class Egg extends AbstractButterfly {
    public Egg(UUID id, int age, int size) {
        super(id, age, size);
    }

    @Override
    public String toString() {
        return "Egg {" +
            "id=" + getId() +
            ", age=" + getAge() +
            ", size=" + getSize() +
            ", alive=" + isAlive() +
            '}';
    }
}
