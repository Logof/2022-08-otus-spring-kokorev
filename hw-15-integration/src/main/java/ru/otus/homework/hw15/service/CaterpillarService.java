package ru.otus.homework.hw15.service;

import ru.otus.homework.hw15.entity.Caterpillar;
import ru.otus.homework.hw15.entity.Chrysalis;

public interface CaterpillarService {

    Chrysalis pupate(Caterpillar caterpillar);
}
