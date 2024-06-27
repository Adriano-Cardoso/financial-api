package com.api.financial.util;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccountUtils {

    public static void validateInitialBalance(BigDecimal initialBalance) {
        if (initialBalance.compareTo(BigDecimal.ZERO) < 0) {
            log.error("Saldo inicial negativo não permitido: {}", initialBalance);
            throw Message.OPERATION_NOT_PERMITTED.asBusinessException();
        }
    }

    public static void validateBalance(BigDecimal balance, String accountNumber) {
        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            log.error("Operação não permitida: saldo resultante negativo para a conta {}", accountNumber);
            throw Message.OPERATION_NOT_PERMITTED.asBusinessException(
                    "Operação não permitida: saldo resultante negativo para a conta " + accountNumber);
        }
    }

    public static String generateTransactionId() {
        return UUID.randomUUID().toString();
    }
}
