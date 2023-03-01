package ru.otus.homework.hw15.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.homework.hw15.entity.Caterpillar;
import ru.otus.homework.hw15.entity.Egg;
import ru.otus.homework.hw15.service.EggService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.concurrent.ThreadLocalRandom.current;

@Service
@Slf4j
public class EggServiceImpl implements EggService {
    @Override
    public Caterpillar hatch(Egg egg) {
        log.info("Начинаем вылупление из яйца: {}", egg);
        for (int i = 1; i < current().nextInt(1, 10); i++) {
            egg.setSize(egg.getSize() + 2);
            egg.setAge(egg.getAge() + 1);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Caterpillar caterpillar = new Caterpillar(egg.getId(), egg.getAge(), egg.getSize());
        log.info("Текущая стадия: {}", caterpillar);
        return caterpillar;
    }

    @Override
    public List<Egg> generate() {
        List<Egg> eggs = new ArrayList<>();
        for (int i = 0; i < /*current().nextInt(1, 10)*/1; i++) {
            eggs.add(new Egg(UUID.randomUUID(), 0, 0));
        }
        return eggs;
    }
}
