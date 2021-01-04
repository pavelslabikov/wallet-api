package models;

public enum TransactionType {
    INCOME("income"), OUTCOME("outcome");

    private final String type;

    TransactionType(String type) {
        this.type = type;
    }

    public String getRepresentation() {  // TODO: Rename
        return type;
    }
}
