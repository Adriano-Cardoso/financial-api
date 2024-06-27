package com.api.financial.domain.model.dto.inbound;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountInbound {

    private Long userId;

    private String accountType;

    private String accountNumber;

    private String transactionId;

    private BigDecimal initialBalance;
}
