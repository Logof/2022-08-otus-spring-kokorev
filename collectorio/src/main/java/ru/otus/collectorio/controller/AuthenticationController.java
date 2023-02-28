package ru.otus.collectorio.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.collectorio.exception.BaseException;
import ru.otus.collectorio.payload.request.LoginRequest;
import ru.otus.collectorio.payload.request.RegisterRequest;
import ru.otus.collectorio.payload.response.EntityResponse;
import ru.otus.collectorio.payload.response.security.UserInfo;
import ru.otus.collectorio.payload.response.security.UserToken;
import ru.otus.collectorio.service.impl.AuthenticationServiceImpl;

@RestController
@Tag(name = "Авторизация пользователя")
public class AuthenticationController {

    private final AuthenticationServiceImpl authenticationService;
    public AuthenticationController(AuthenticationServiceImpl authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(path = "/api/auth/register")
    @Operation(summary = "Регистрация нового пользователя")
    public EntityResponse register(@RequestBody RegisterRequest registerRequest) {
        try {
            UserInfo userInfo = authenticationService.registration(registerRequest);
            return EntityResponse.success(userInfo);
        } catch (BaseException e) {
            return EntityResponse.error(e.getMessage());
        }
    }
    @PostMapping(path = "/api/auth/login")
    @Operation(summary = "Авторизация пользователя")
    public EntityResponse login(@RequestBody LoginRequest loginRequest) {
        try {
            UserToken userToken = authenticationService.login(loginRequest);
            return EntityResponse.success(userToken);
        } catch (BaseException e) {
            return EntityResponse.error(e.getMessage());
        }
    }

    @GetMapping(path = "/api/auth/user/info")
    @Operation(summary = "Информация о текущем пользователе")
    @SecurityRequirement(name = "Bearer Authentication")
    public EntityResponse getInfo() {
        try {
            UserInfo userInfo = authenticationService.getInfo();
            return EntityResponse.success(userInfo);
        } catch (BaseException e) {
            return EntityResponse.error(e.getMessage());
        }
    }
}
