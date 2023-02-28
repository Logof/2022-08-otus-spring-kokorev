package ru.otus.collectorio.payload.response.security;

import lombok.Getter;
import lombok.Setter;
import ru.otus.collectorio.entity.Role;

import java.util.List;

@Getter
@Setter
public class UserInfo {
    private Long id;

    private String username;

    private String password;

    private String email;

    private List<Role> roles;
}
