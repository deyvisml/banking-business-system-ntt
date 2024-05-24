package com.example.BankAccountsService.types;

public enum OperationType {
    DEPOSITO("Deposito"),
    RETIRO("Retiro");

    private final String label;

    OperationType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}