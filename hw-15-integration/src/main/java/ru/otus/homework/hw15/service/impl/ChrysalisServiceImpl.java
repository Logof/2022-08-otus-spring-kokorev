package ru.otus.homework.hw15.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.homework.hw15.entity.Butterfly;
import ru.otus.homework.hw15.entity.Chrysalis;
import ru.otus.homework.hw15.service.ChrysalisService;

import static java.util.concurrent.ThreadLocalRandom.current;

@Service
@Slf4j
public class ChrysalisServiceImpl implements ChrysalisService {
    @Override
    public Butterfly transform(Chrysalis chrysalis) {
        log.info("\t\t>>> Начинаем трансформацию из куколки {}", chrysalis);
        for (int i = 1; i < current().nextInt(1, 40); i++) {
            chrysalis.setSize(chrysalis.getSize() - 1);
            chrysalis.setAge(chrysalis.getAge() + 1);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Butterfly butterfly = new Butterfly(chrysalis.getId(), chrysalis.getAge(), chrysalis.getSize());
        log.info("\t\t<<< Готовая бабочка: {}", butterfly);
        return butterfly;
    }
}
