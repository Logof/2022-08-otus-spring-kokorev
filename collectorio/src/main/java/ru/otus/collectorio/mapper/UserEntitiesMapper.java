package ru.otus.collectorio.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.otus.collectorio.entity.UserEntity;
import ru.otus.collectorio.payload.request.LoginRequest;
import ru.otus.collectorio.payload.request.RegisterRequest;
import ru.otus.collectorio.payload.response.security.UserInfo;

@Mapper
public interface UserEntitiesMapper {

    @Mapping(target = "username", source = "username")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "id", ignore = true)
    UserEntity toUserEntity(RegisterRequest request);


    @Mapping(target = "password", source = "password")
    LoginRequest toLoginRequest(RegisterRequest request);

    UserInfo toUserInfo(UserEntity userEntity);
}
