package com.api.financial.domain.port;

import com.api.financial.domain.model.dto.inbound.UpdateUserInbound;
import com.api.financial.domain.model.dto.inbound.UserInbound;
import com.api.financial.domain.model.dto.outbound.UserOutbound;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserPort {

    UserOutbound save(UserInbound userInbound);
    UserOutbound update(String email, UpdateUserInbound updateUserInbound);
    Page<UserOutbound> searchRegisteredUsers(Pageable pageable, String identifier);
    void delete(Long userId);


}
