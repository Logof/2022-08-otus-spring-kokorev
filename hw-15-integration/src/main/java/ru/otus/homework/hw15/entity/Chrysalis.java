package ru.otus.homework.hw15.entity;

import java.util.UUID;

public class Chrysalis extends AbstractButterfly {
    public Chrysalis(UUID id, int age, int size) {
        super(id, age, size);
    }

    @Override
    public String toString() {
        return "Chrysalis{" +
            "id=" + getId() +
            ", age=" + getAge() +
            ", size=" + getSize() +
            ", alive=" + isAlive() +
            '}';
    }
}
