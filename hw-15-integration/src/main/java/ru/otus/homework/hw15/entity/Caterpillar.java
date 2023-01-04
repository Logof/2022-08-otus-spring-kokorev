package ru.otus.homework.hw15.entity;

import java.util.UUID;

public class Caterpillar extends AbstractButterfly {
    public Caterpillar(UUID id, int age, int size) {
        super(id, age, size);
    }

    @Override
    public String toString() {
        return "Caterpillar {" + "id=" + getId() + ", age=" + getAge() +
                ", size=" + getSize() + ", alive=" + isAlive() + "}";
    }
}
