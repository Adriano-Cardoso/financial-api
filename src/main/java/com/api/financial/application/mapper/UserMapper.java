package com.api.financial.application.mapper;

import com.api.financial.domain.model.User;
import com.api.financial.domain.model.dto.inbound.UpdateUserInbound;
import com.api.financial.domain.model.dto.inbound.UserInbound;
import com.api.financial.domain.model.dto.outbound.UserOutbound;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "name", source = "name") // Change to "username"
    @Mapping(target = "email", source = "email") // Change to "email"
    @Mapping(target = "userId", ignore = true)
    User userInboundToUser(UserInbound userInbound);

    UserOutbound userToUserOutbound(User user);

    User updateUserInboundToUser(UpdateUserInbound updateUserInbound);
}
