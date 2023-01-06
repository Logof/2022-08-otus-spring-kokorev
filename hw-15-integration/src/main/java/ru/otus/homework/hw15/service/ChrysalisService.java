package ru.otus.homework.hw15.service;

import ru.otus.homework.hw15.entity.Butterfly;
import ru.otus.homework.hw15.entity.Chrysalis;

public interface ChrysalisService {

    Butterfly transform(Chrysalis chrysalis);
}
