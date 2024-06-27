package com.api.financial.application.usecase;

import com.api.financial.domain.model.dto.inbound.UpdateUserInbound;
import com.api.financial.domain.model.dto.inbound.UserInbound;
import com.api.financial.domain.model.dto.outbound.UserOutbound;
import org.springframework.data.domain.Page;

public interface UserUseCase {

    UserOutbound save(UserInbound createUserInbound);
    Page<UserOutbound> fetchAllUsers(int page, int limit, String identifier);
    UserOutbound updateUser(String email, UpdateUserInbound updateUserInbound);
    void deleteUser(Long userId);
}
