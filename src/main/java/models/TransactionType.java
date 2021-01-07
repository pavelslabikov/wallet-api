package models;

import org.codehaus.jackson.annotate.JsonValue;


public enum TransactionType {
    INCOME("income"), OUTCOME("outcome");

    private final String type;

    TransactionType(String type) {
        this.type = type;
    }

    @JsonValue
    public String getType() {  // TODO: Rename
        return type;
    }
}
