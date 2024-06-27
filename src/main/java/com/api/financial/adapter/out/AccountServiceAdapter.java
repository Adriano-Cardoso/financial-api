package com.api.financial.adapter.out;

import com.api.financial.adapter.out.repository.AccountRepository;
import com.api.financial.application.mapper.AccountMapper;
import com.api.financial.domain.model.Account;
import com.api.financial.domain.model.dto.inbound.AccountInbound;
import com.api.financial.domain.model.dto.outbound.AccountOutbound;
import com.api.financial.domain.port.AccountServicePort;
import com.api.financial.util.AccountUtils;
import com.api.financial.util.Message;
import com.api.financial.util.UserUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@RequiredArgsConstructor
@Component
public class AccountServiceAdapter implements AccountServicePort {

    private final UserUtils userUtils;
    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public AccountOutbound createAccount(AccountInbound accountInbound) {
        AccountUtils.validateInitialBalance(accountInbound.getInitialBalance());

        userUtils.fetchUserById(accountInbound.getUserId());

        Account account = accountMapper.toRepresentationConvertionInboundToAccount(accountInbound);
        account.setTransactionId(AccountUtils.generateTransactionId());
        accountRepository.save(account);

        log.info("Conta criada com sucesso: {}", account.getId());
        return accountMapper.toRepresentationConvertionAccountToOutbound(account);
    }

    @Override
    @Transactional
    public AccountOutbound updateBalance(AccountInbound accountInbound, BigDecimal amount) {
        Account account = accountRepository.findByTransactionId(accountInbound.getTransactionId())
                .orElseThrow(Message.TRANSACTION_ID_NOT_FOUND::asBusinessException);

        BigDecimal newBalance = account.getBalance().add(amount);
        AccountUtils.validateBalance(newBalance, accountInbound.getAccountNumber());

        account.setBalance(newBalance);
        account.setTransactionId(AccountUtils.generateTransactionId());
        accountRepository.save(account);

        log.info("Saldo atualizado com sucesso para a conta {}: Novo saldo: {}", account.getId(), newBalance);
        return accountMapper.toRepresentationConvertionAccountToOutbound(account);
    }

    @Override
    public AccountOutbound findById(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> Message.ACCOUNT_NOT_FOUND.asBusinessException("Conta não encontrada: " + accountId));

        log.info("Conta encontrada: {}", account.getId());
        return accountMapper.toRepresentationConvertionAccountToOutbound(account);
    }

    @Override
    public void deleteById(Long accountId) {
        findById(accountId);

        accountRepository.deleteById(accountId);
        log.info("Conta deletada com sucesso: {}", accountId);
    }
}