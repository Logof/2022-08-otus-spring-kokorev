package ru.otus.collectorio.service;

import ru.otus.collectorio.payload.request.LoginRequest;
import ru.otus.collectorio.payload.request.RegisterRequest;
import ru.otus.collectorio.payload.response.security.UserInfo;
import ru.otus.collectorio.payload.response.security.UserToken;

public interface AuthenticationService {
    UserInfo registration(RegisterRequest registerRequest) throws Exception;

    UserToken login(LoginRequest loginRequest) throws Exception;

    UserInfo getInfo();
}
