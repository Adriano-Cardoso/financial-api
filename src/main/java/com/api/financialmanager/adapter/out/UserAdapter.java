package com.api.financialmanager.adapter.out;

import com.api.financialmanager.adapter.out.repository.ProfileRepository;
import com.api.financialmanager.adapter.out.repository.UserRepository;
import com.api.financialmanager.application.mapper.UserMapper;
import com.api.financialmanager.domain.model.Profile;
import com.api.financialmanager.domain.model.User;
import com.api.financialmanager.domain.model.dto.inbound.UpdateUserInbound;
import com.api.financialmanager.domain.model.dto.inbound.UserInbound;
import com.api.financialmanager.domain.model.dto.outbound.UserOutbound;
import com.api.financialmanager.domain.port.UserPort;
import com.api.financialmanager.util.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserAdapter implements UserPort {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserMapper userMapper;


    @Override
    public UserOutbound save(UserInbound userInbound) {
        log.info("Salvando usuário - Nome de usuário: {}, Email: {}", userInbound.getName(),
                userInbound.getEmail());

        userRepository.findByEmail(userInbound.getEmail()).ifPresent(p -> {
            throw Message.IS_PRESENT_USER.asBusinessException();
        });

        User user = userMapper.userInboundToUser(userInbound);

        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        user.setProfiles(Collections.singletonList(fetchProfile()));

        userRepository.save(user);

        return userMapper.userToUserOutbound(user);
    }

    @Override
    public UserOutbound update(String email, UpdateUserInbound updateUserInbound) {

        this.userRepository.findByEmail(email)
                .ifPresent(e -> {
                    throw Message.EMAIL_NOT_FOUND.asBusinessException();
                });

        User user = userMapper.updateUserInboundToUser(updateUserInbound);

        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        log.info("Atualizando usuário - ID: {}, Nome: {}", user.getUserId(), user.getName());
        return userMapper.userToUserOutbound(user);
    }

    @Override
    public Page<UserOutbound> searchRegisteredUsers(Pageable pageable, String identifier) {
        log.info("Buscando usuários registrados - Identificador: {}", identifier);
        return userRepository.searchRegisteredUsers(pageable, identifier);
    }

    @Override
    public void delete(Long userId) {
        User user = fetchUserById(userId);
        userRepository.delete(user);
        log.info("Usuário deletado com sucesso - ID: {}", userId);
    }

    @Override
    public User fetchUserById(Long userId) {
        log.info("Buscando usuário por ID: {}", userId);
        return userRepository.findById(userId)
                .orElseThrow(Message.USER_NOT_FOUND::asBusinessException);
    }

    private Profile fetchProfile() {
        log.debug("Buscando perfil: {}", "USER");
        return profileRepository.findByName("USER")
                .orElseThrow(Message.NAME_PROFILE_NOT_FOUND::asBusinessException);
    }
}
