package ru.otus.homework.hw15.integration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.homework.hw15.entity.AbstractButterfly;
import ru.otus.homework.hw15.entity.Egg;

import java.util.List;

@MessagingGateway
public interface ButterflyGateway {

    @Gateway(requestChannel = "eggsChannel", replyChannel = "butterflyChannel")
    List<AbstractButterfly> transform(List<Egg> eggs);
}
