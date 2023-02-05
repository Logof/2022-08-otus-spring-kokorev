package ru.otus.collectorio.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import ru.otus.collectorio.entity.Role;
import ru.otus.collectorio.entity.UserEntity;
import ru.otus.collectorio.exception.BaseException;
import ru.otus.collectorio.exception.UserException;
import ru.otus.collectorio.mapper.UserEntitiesMapper;
import ru.otus.collectorio.payload.request.LoginRequest;
import ru.otus.collectorio.payload.request.RegisterRequest;
import ru.otus.collectorio.payload.response.entity.UserInfo;
import ru.otus.collectorio.repository.UserRepository;
import ru.otus.collectorio.security.jwt.JwtTokenProvider;
import ru.otus.collectorio.service.AuthenticationService;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    private final JwtTokenProvider jwtTokenProvider;

    private final UserEntitiesMapper mapper;

    public AuthenticationServiceImpl(UserRepository userRepository,
                                     PasswordEncoder passwordEncoder,
                                     JwtTokenProvider jwtTokenProvider,
                                     UserEntitiesMapper mapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public UserInfo registration(RegisterRequest registerRequest) throws BaseException {
        if (Objects.isNull(registerRequest.getUsername()) || registerRequest.getUsername().isEmpty()) {
            throw UserException.notFound();
        }

        if (Objects.isNull(registerRequest.getPassword()) || registerRequest.getPassword().isEmpty()) {
            throw UserException.notFound();
        }

        if (Objects.isNull(registerRequest.getEmail()) || registerRequest.getEmail().isEmpty()) {
            throw UserException.notFound();
        }

        registerRequest.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        UserEntity user = mapper.toUserEntity(registerRequest);

        List<Role> roleList = Collections.singletonList(Role.ROLE_USER);
        user.setRoles(roleList);
        userRepository.save(user);

        UserInfo userInfo = mapper.toUserInfo(user);
        userInfo.setToken(jwtTokenProvider.createToken(user));

        userRepository.aclAddUser(userInfo);
        return userInfo;
    }

    @Override
    @Transactional(readOnly = true)
    public UserInfo login(@RequestBody LoginRequest loginRequest) throws BaseException {
        if (Objects.isNull(loginRequest.getUsernameOrEmail()) || loginRequest.getUsernameOrEmail().isEmpty()) {
            throw UserException.notFound();
        }

        if (Objects.isNull(loginRequest.getPassword()) || loginRequest.getPassword().isEmpty()) {
            throw UserException.notFound();
        }
        UserEntity user = userRepository.findByUsername(loginRequest.getUsernameOrEmail())
                .orElseThrow(() -> UserException.notFound());

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw UserException.notFound();
        }
        UserInfo userInfo = mapper.toUserInfo(user);
        userInfo.setToken(jwtTokenProvider.createToken(user));
        return userInfo;
    }

}
