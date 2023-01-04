package ru.otus.homework.hw15.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.homework.hw15.entity.AbstractButterfly;
import ru.otus.homework.hw15.entity.Egg;
import ru.otus.homework.hw15.integration.ButterflyGateway;
import ru.otus.homework.hw15.service.ButterflyService;
import ru.otus.homework.hw15.service.EggService;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ButterflyServiceImpl implements ButterflyService {

    private final ButterflyGateway butterflyGateway;
    private final EggService eggService;

    public ButterflyServiceImpl(ButterflyGateway butterflyGateway, EggService eggService) {
        this.butterflyGateway = butterflyGateway;
        this.eggService = eggService;
    }

    @Override
    public void launchIncubator() {
        ForkJoinPool pool = ForkJoinPool.commonPool();
        for (int i = 0; i < 10; i++) {
            pool.execute(() -> {
                List<Egg> eggs = eggService.generate();
                log.info(">>> Новые яйца: {}", eggs.stream().map(Egg::toString).collect(Collectors.toList()));
                List<AbstractButterfly> butterflies = butterflyGateway.transform(eggs);
                log.info("<<< Полученные бабочки: {}", butterflies.stream().map(AbstractButterfly::toString).collect(Collectors.toList()));
            });
            try {
                Thread.sleep(1_000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
