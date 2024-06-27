package com.api.financial.domain.service;

import com.api.financial.application.usecase.AccountUseCase;
import com.api.financial.domain.model.dto.inbound.AccountInbound;
import com.api.financial.domain.model.dto.outbound.AccountOutbound;
import com.api.financial.domain.port.AccountServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class AccountService implements AccountUseCase {

    private  final AccountServicePort accountServicePort;

    @Override
    public AccountOutbound createAccount(AccountInbound accountInbound) {
        return accountServicePort.createAccount(accountInbound);
    }

    @Override
    public AccountOutbound updateBalance(AccountInbound accountInbound, BigDecimal amount) {
        return accountServicePort.updateBalance(accountInbound, amount);
    }

    @Override
    public AccountOutbound findById(Long accountId) {
        return accountServicePort.findById(accountId);
    }

    @Override
    public void deleteById(Long accountId) {
        accountServicePort.deleteById(accountId);

    }
}
