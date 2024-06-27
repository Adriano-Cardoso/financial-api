package com.api.financial.application.usecase;

import com.api.financial.domain.model.dto.inbound.LoginInbound;
import com.api.financial.domain.model.dto.outbound.LoginOutbound;

import java.util.List;

public interface LoginUseCase {

    LoginOutbound authenticateAndGenerateToken(LoginInbound loginInbound);
    String createToken(String username, List<String> profiles);
    String validateToken(String token);
}
