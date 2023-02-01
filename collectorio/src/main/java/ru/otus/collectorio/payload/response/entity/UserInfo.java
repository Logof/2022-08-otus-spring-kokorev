package ru.otus.collectorio.payload.response.entity;

import lombok.Getter;
import lombok.Setter;
import ru.otus.collectorio.entity.UserEntity;

@Getter
@Setter
public class UserInfo extends UserEntity {

    private String token;
}
