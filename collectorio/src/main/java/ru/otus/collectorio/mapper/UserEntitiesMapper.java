package ru.otus.collectorio.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.otus.collectorio.entity.UserEntity;
import ru.otus.collectorio.payload.request.LoginRequest;
import ru.otus.collectorio.payload.request.RegisterRequest;
import ru.otus.collectorio.payload.response.entity.UserInfo;

@Mapper
public interface UserEntitiesMapper {

    @Mapping(target = "firstName", source = "username")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "id", ignore = true)
    UserEntity toUserEntity(RegisterRequest request);


    @Mapping(target = "usernameOrEmail", source = "username")
    @Mapping(target = "password", source = "password")
    LoginRequest toLoginRequest(RegisterRequest request);


    @Mapping(target = "token", ignore = true)
    UserInfo toUserInfo(UserEntity userEntity);
}
