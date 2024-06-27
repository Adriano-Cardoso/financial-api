package com.api.financial.domain.model.enums;

public enum TransactionType {

    DEPOSIT("Depósito"),
    WITHDRAWAL("Saque"),
    TRANSFER("Transferência"),
    PIX("PIX");

    private final String description;

    TransactionType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
