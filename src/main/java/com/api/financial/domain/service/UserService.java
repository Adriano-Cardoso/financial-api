package com.api.financial.domain.service;

import com.api.financial.adapter.out.repository.UserRepository;
import com.api.financial.application.usecase.UserUseCase;
import com.api.financial.domain.model.dto.inbound.UpdateUserInbound;
import com.api.financial.domain.model.dto.inbound.UserInbound;
import com.api.financial.domain.model.dto.outbound.UserOutbound;
import com.api.financial.domain.port.UserPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService implements UserUseCase, UserDetailsService {

    private final UserRepository userRepository;
    private final UserPort userPort;

    @Override
    public UserOutbound save(UserInbound userInbound) {
        return userPort.save(userInbound);
    }

    @Override
    public Page<UserOutbound> fetchAllUsers(int page, int limit, String identifier) {
        Pageable pageable = PageRequest.of(page, limit);

        log.info("method=searchRegisteredPatients Name={} limit={}", identifier, limit);

        return userPort.searchRegisteredUsers(pageable, identifier);
    }

    @Override
    public UserOutbound updateUser(String email, UpdateUserInbound updateUserInbound) {
        return userPort.update(email, updateUserInbound);
    }

    @Override
    public void deleteUser(Long userId) {
        log.info("method=deleteUser userId={}", userId);
        this.userPort.delete(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("method=loadUserByUsername username={}", username);
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
    }
}
