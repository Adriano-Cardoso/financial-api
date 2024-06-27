package com.api.financialmanager.application.usecase;

import com.api.financialmanager.domain.model.dto.inbound.LoginInbound;
import com.api.financialmanager.domain.model.dto.outbound.LoginOutbound;

import java.util.List;

public interface LoginUseCase {

    LoginOutbound authenticateAndGenerateToken(LoginInbound loginInbound);
    String createToken(String username, List<String> profiles);
    String validateToken(String token);
}
