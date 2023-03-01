package ru.otus.homework.hw15.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.homework.hw15.entity.Caterpillar;
import ru.otus.homework.hw15.entity.Chrysalis;
import ru.otus.homework.hw15.service.CaterpillarService;

import static java.util.concurrent.ThreadLocalRandom.current;

@Service
@Slf4j
public class CaterpillarServiceImpl implements CaterpillarService {
    @Override
    public Chrysalis pupate(Caterpillar caterpillar) {
        log.info("\t>>> Начинаем окукливание {}", caterpillar);
        for (int i = 1; i < current().nextInt(1, 10); i++) {
            caterpillar.setSize(caterpillar.getSize() + 2);
            caterpillar.setAge(caterpillar.getAge() + 1);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Chrysalis chrysalis = new Chrysalis(caterpillar.getId(), caterpillar.getAge(), caterpillar.getSize());
        log.info("\t<<< Готовая куколка: {}", chrysalis);
        return chrysalis;
    }
}
