package com.api.financial.domain.port;

import com.api.financial.domain.model.dto.inbound.AccountInbound;
import com.api.financial.domain.model.dto.outbound.AccountOutbound;

import java.math.BigDecimal;

public interface AccountServicePort {

    AccountOutbound createAccount(AccountInbound accountInbound);

    AccountOutbound updateBalance(AccountInbound account, BigDecimal amount);

    AccountOutbound findById(Long accountId);

    void deleteById(Long accountId);
}
