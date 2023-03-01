package ru.otus.homework.hw16.actuators;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AdminIndicator implements HealthIndicator {

    @Override
    public Health health() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getName().equalsIgnoreCase("admin")) {
            return Health.down()
                    .status(Status.DOWN)
                    .withDetail("message", "Всем стоять, работает админ")
                    .build();

        }
        return Health.up().withDetail("message", "На сервере все спокойно!").build();
    }
}
