package ru.otus.collectorio.service;

import ru.otus.collectorio.payload.request.LoginRequest;
import ru.otus.collectorio.payload.request.RegisterRequest;
import ru.otus.collectorio.payload.response.entity.UserInfo;

public interface AuthenticationService {
    UserInfo registration(RegisterRequest registerRequest) throws Exception;

    UserInfo login(LoginRequest loginRequest) throws Exception;
}
