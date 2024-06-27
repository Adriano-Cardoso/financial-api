package com.api.financial.domain.port;

import com.api.financial.domain.model.dto.inbound.LoginInbound;
import com.api.financial.domain.model.dto.outbound.LoginOutbound;

import java.util.List;

public interface AuthenticationPort {

    LoginOutbound authenticateAndGenerateToken(LoginInbound loginInbound);
    String createToken(String username, List<String> profiles);
    String validateToken(String token);
}
