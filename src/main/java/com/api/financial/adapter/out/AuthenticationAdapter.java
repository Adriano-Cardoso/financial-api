package com.api.financial.adapter.out;

import com.api.financial.application.mapper.TokenMapper;
import com.api.financial.domain.model.Profile;
import com.api.financial.domain.model.User;
import com.api.financial.domain.model.dto.inbound.LoginInbound;
import com.api.financial.domain.model.dto.outbound.LoginOutbound;
import com.api.financial.domain.port.AuthenticationPort;
import com.api.financial.util.Message;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthenticationAdapter implements AuthenticationPort {

    @Value("${api.jwt.secret}")
    private String secretKey;

    @Value("${api.jwt.expiration}")
    private Long expiration;

    private final AuthenticationManager authenticationManager;
    private final TokenMapper tokenMapper;

    @Override
    public LoginOutbound authenticateAndGenerateToken(LoginInbound loginInbound) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginInbound.getEmail(), loginInbound.getPassword()));

            User user = (User) auth.getPrincipal();
            String token = createToken(user.getEmail(), user.getProfiles().stream().map(Profile::getName).toList());

            return tokenMapper.mapToDto(token);
        } catch (BadCredentialsException e) {
            log.error("Failed to authenticate user: {}", loginInbound.getEmail(), e);
            throw Message.INVALID_CREDENTIALS.asBusinessException();
        }
    }

    @Override
    public String createToken(String username, List<String> profiles) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            return JWT.create()
                    .withIssuer("bearer")
                    .withSubject(username)
                    .withClaim("financial-manager-api", profiles)
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            log.error("Failed to create token for user: {}", username, exception);
            throw Message.TOKEN_ERROR.asBusinessException();
        }
    }

    @Override
    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            return JWT.require(algorithm)
                    .withIssuer("financial-manager-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            log.error("Failed to validate token: {}", token, exception);
            throw Message.TOKEN_ERROR.asBusinessException();
        }
    }

    private Instant genExpirationDate() {
        return Instant.now().plusMillis(expiration);
    }

}
