package ru.otus.collectorio.payload.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public class Token {
    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("token")
    private String token;
}
