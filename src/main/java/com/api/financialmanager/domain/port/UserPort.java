package com.api.financialmanager.domain.port;

import com.api.financialmanager.domain.model.User;
import com.api.financialmanager.domain.model.dto.inbound.UpdateUserInbound;
import com.api.financialmanager.domain.model.dto.inbound.UserInbound;
import com.api.financialmanager.domain.model.dto.outbound.UserOutbound;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserPort {

    UserOutbound save(UserInbound userInbound);
    UserOutbound update(String email, UpdateUserInbound updateUserInbound);
    Page<UserOutbound> searchRegisteredUsers(Pageable pageable, String identifier);
    void delete(Long userId);
    User fetchUserById(Long userId);


}
