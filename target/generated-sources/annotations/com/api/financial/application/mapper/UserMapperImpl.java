package com.api.financial.application.mapper;

import com.api.financial.domain.model.User;
import com.api.financial.domain.model.dto.inbound.UpdateUserInbound;
import com.api.financial.domain.model.dto.inbound.UserInbound;
import com.api.financial.domain.model.dto.outbound.UserOutbound;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-27T16:23:15-0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User userInboundToUser(UserInbound userInbound) {
        if ( userInbound == null ) {
            return null;
        }

        User user = new User();

        user.setName( userInbound.getName() );
        user.setEmail( userInbound.getEmail() );
        user.setPassword( userInbound.getPassword() );

        return user;
    }

    @Override
    public UserOutbound userToUserOutbound(User user) {
        if ( user == null ) {
            return null;
        }

        UserOutbound userOutbound = new UserOutbound();

        userOutbound.setUserId( user.getUserId() );
        userOutbound.setName( user.getName() );
        userOutbound.setEmail( user.getEmail() );
        userOutbound.setPassword( user.getPassword() );

        return userOutbound;
    }

    @Override
    public User updateUserInboundToUser(UpdateUserInbound updateUserInbound) {
        if ( updateUserInbound == null ) {
            return null;
        }

        User user = new User();

        user.setName( updateUserInbound.getName() );
        user.setPassword( updateUserInbound.getPassword() );

        return user;
    }
}
