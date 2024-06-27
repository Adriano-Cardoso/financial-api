package com.api.financial.util;

import com.api.financial.adapter.out.repository.UserRepository;
import com.api.financial.domain.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserUtils {

    private final UserRepository userRepository;

    public User fetchUserById(Long userId) {
        log.info("Buscando usuário por ID: {}", userId);
        return userRepository.findById(userId)
                .orElseThrow(Message.USER_NOT_FOUND::asBusinessException);
    }
}
