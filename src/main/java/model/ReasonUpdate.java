package model;

public enum ReasonUpdate {
    COMPETE_QUEST("COMPETE_QUEST"),
    FROM_WALLET("FROM_WALLET");

    private final String value;

    ReasonUpdate(String value) {
        this.value = value;
    }

    public String get() {
        return value;
    }
}
