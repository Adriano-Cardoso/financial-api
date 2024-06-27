package com.api.financialmanager.domain.service;

import com.api.financialmanager.domain.model.dto.inbound.LoginInbound;
import com.api.financialmanager.domain.model.dto.outbound.LoginOutbound;
import com.api.financialmanager.application.usecase.LoginUseCase;
import com.api.financialmanager.domain.port.AuthenticationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginService implements LoginUseCase {

    private final AuthenticationPort authenticationPort;

    @Override
    public LoginOutbound authenticateAndGenerateToken(LoginInbound loginInbound) {
        return authenticationPort.authenticateAndGenerateToken(loginInbound);
    }

    @Override
    public String createToken(String username, List<String> profiles) {
        return authenticationPort.createToken(username, profiles);
    }

    @Override
    public String validateToken(String token) {
        return authenticationPort.validateToken(token);
    }
}
