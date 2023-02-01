package ru.otus.collectorio.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.otus.collectorio.entity.Role;
import ru.otus.collectorio.entity.UserEntity;
import ru.otus.collectorio.payload.request.LoginRequest;
import ru.otus.collectorio.payload.request.RegisterRequest;
import ru.otus.collectorio.payload.response.entity.UserInfo;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-01T22:27:23+0500",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.18 (Oracle Corporation)"
)
@Component
public class UserEntitiesMapperImpl implements UserEntitiesMapper {

    @Override
    public UserEntity toUserEntity(RegisterRequest request) {
        if ( request == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setFirstName( request.getUsername() );
        userEntity.setLastName( request.getLastName() );
        userEntity.setUsername( request.getUsername() );
        userEntity.setPassword( request.getPassword() );

        return userEntity;
    }

    @Override
    public LoginRequest toLoginRequest(RegisterRequest request) {
        if ( request == null ) {
            return null;
        }

        LoginRequest loginRequest = new LoginRequest();

        loginRequest.setUsernameOrEmail( request.getUsername() );
        loginRequest.setPassword( request.getPassword() );

        return loginRequest;
    }

    @Override
    public UserInfo toUserInfo(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserInfo userInfo = new UserInfo();

        userInfo.setId( userEntity.getId() );
        userInfo.setFirstName( userEntity.getFirstName() );
        userInfo.setLastName( userEntity.getLastName() );
        userInfo.setUsername( userEntity.getUsername() );
        userInfo.setPassword( userEntity.getPassword() );
        List<Role> list = userEntity.getAuthorities();
        if ( list != null ) {
            userInfo.setAuthorities( new ArrayList<Role>( list ) );
        }

        return userInfo;
    }
}
