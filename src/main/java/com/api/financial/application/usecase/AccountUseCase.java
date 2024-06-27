package com.api.financial.application.usecase;

import com.api.financial.domain.model.dto.inbound.AccountInbound;
import com.api.financial.domain.model.dto.outbound.AccountOutbound;

import java.math.BigDecimal;

public interface AccountUseCase {

    AccountOutbound createAccount(AccountInbound accountInbound);

    AccountOutbound updateBalance(AccountInbound accountInbound, BigDecimal amount);

    AccountOutbound findById(Long accountId);

    void deleteById(Long accountId);

}
