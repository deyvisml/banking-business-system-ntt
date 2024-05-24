package com.example.BankAccountsService.types;

public enum AccountType {
    AHORRO("Ahorro"),
    CORRIENTE("Corriente"),
    PLAZO_FIJO("Plazo fijo");
    private final String label;

    AccountType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}