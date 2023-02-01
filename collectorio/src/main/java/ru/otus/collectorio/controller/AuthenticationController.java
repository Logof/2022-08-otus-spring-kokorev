package ru.otus.collectorio.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.collectorio.exception.BaseException;
import ru.otus.collectorio.payload.request.LoginRequest;
import ru.otus.collectorio.payload.request.RegisterRequest;
import ru.otus.collectorio.payload.response.EntityResponse;
import ru.otus.collectorio.payload.response.entity.UserInfo;
import ru.otus.collectorio.service.impl.AuthenticationServiceImpl;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationServiceImpl authenticationService;

    public AuthenticationController(AuthenticationServiceImpl authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public EntityResponse<UserInfo> register(@RequestBody RegisterRequest registerRequest) {
        try {
            UserInfo userInfo = authenticationService.registration(registerRequest);
            return EntityResponse.success(userInfo);
        } catch (BaseException e) {
            return EntityResponse.error(e.getMessage());
        }
    }
    @PostMapping("/login")
    public EntityResponse<UserInfo> login(@RequestBody LoginRequest loginRequest) {
        try {
            UserInfo userInfo = authenticationService.login(loginRequest);
            return EntityResponse.success(userInfo);
        } catch (BaseException e) {
            return EntityResponse.error(e.getMessage());
        }
    }
}
