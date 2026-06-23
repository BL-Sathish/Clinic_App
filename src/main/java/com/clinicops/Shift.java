package com.clinicops;

public enum Shift {
    MORNING("Morning"),
    EVENING("Evening"),
    BOTH("Both");

    private final String displayName;

    Shift(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
