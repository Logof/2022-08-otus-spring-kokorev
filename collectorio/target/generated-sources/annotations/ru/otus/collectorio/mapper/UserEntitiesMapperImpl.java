package ru.otus.collectorio.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.otus.collectorio.entity.Role;
import ru.otus.collectorio.entity.UserEntity;
import ru.otus.collectorio.payload.request.LoginRequest;
import ru.otus.collectorio.payload.request.RegisterRequest;
import ru.otus.collectorio.payload.response.security.UserInfo;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-28T12:24:28+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.18 (Oracle Corporation)"
)
@Component
public class UserEntitiesMapperImpl implements UserEntitiesMapper {

    @Override
    public UserEntity toUserEntity(RegisterRequest request) {
        if ( request == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setUsername( request.getUsername() );
        userEntity.setPassword( request.getPassword() );
        userEntity.setEmail( request.getEmail() );

        return userEntity;
    }

    @Override
    public LoginRequest toLoginRequest(RegisterRequest request) {
        if ( request == null ) {
            return null;
        }

        LoginRequest loginRequest = new LoginRequest();

        loginRequest.setPassword( request.getPassword() );
        loginRequest.setUsername( request.getUsername() );

        return loginRequest;
    }

    @Override
    public UserInfo toUserInfo(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserInfo userInfo = new UserInfo();

        userInfo.setId( userEntity.getId() );
        userInfo.setUsername( userEntity.getUsername() );
        userInfo.setPassword( userEntity.getPassword() );
        userInfo.setEmail( userEntity.getEmail() );
        List<Role> list = userEntity.getRoles();
        if ( list != null ) {
            userInfo.setRoles( new ArrayList<Role>( list ) );
        }

        return userInfo;
    }
}
