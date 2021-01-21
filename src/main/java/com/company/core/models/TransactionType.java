package com.company.core.models;

import com.fasterxml.jackson.annotation.JsonValue;


public enum TransactionType {
    INCOME("income"), OUTCOME("outcome");

    private final String name;

    TransactionType(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }
}
