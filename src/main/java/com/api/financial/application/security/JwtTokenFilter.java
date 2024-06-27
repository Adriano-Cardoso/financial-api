package com.api.financial.application.security;

import com.api.financial.adapter.out.repository.UserRepository;
import com.api.financial.domain.model.User;
import com.api.financial.domain.service.LoginService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private final LoginService loginService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = recoverToken(request);
            if (token != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                String login = loginService.validateToken(token);
                Optional<User> userOpt = userRepository.findByEmail(login);

                if (userOpt.isPresent()) {
                    User user = userOpt.get();
                    log.info("User found: {}", user.getUsername());

                    UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
                            .password(user.getPassword())
                            .authorities(user.getAuthorities())
                            .build();

                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                    log.info("Authentication set in SecurityContextHolder");
                } else {
                    log.warn("User not found for login: {}", login);
                }
            }
        } catch (Exception e) {
            log.error("An error occurred while processing the token", e);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            log.info("Authorization header not found or does not start with Bearer");
            return null;
        }
        String token = authHeader.substring(7);
        log.info("Recovered token: {}", token);
        return token;
    }
}
