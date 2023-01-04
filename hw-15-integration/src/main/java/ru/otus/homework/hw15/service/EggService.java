package ru.otus.homework.hw15.service;

import ru.otus.homework.hw15.entity.Caterpillar;
import ru.otus.homework.hw15.entity.Egg;

import java.util.List;

public interface EggService {

    Caterpillar hatch(Egg egg);

    List<Egg> generate();
}
