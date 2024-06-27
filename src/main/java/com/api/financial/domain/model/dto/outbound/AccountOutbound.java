package com.api.financial.domain.model.dto.outbound;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountOutbound {

    private Long id;
    private Long userId;
    private String accountType;
    private BigDecimal balance;
    private String accountNumber;
    private String transactionId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
