package ru.otus.collectorio.service;

import ru.otus.collectorio.exception.BaseException;
import ru.otus.collectorio.exception.UserException;
import ru.otus.collectorio.payload.request.LoginRequest;
import ru.otus.collectorio.payload.request.RegisterRequest;
import ru.otus.collectorio.payload.response.entity.UserInfo;

public interface AuthenticationService {
    UserInfo registration(RegisterRequest registerRequest) throws BaseException;

    UserInfo login(LoginRequest loginRequest) throws UserException;
}
