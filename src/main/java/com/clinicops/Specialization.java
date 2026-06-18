package com.clinicops;

public enum Specialization {
    CARDIOLOGY("Cardiology"),
    DERMATOLOGY("Dermatology"),
    NEUROLOGY("Neurology"),
    PEDIATRICS("Pediatrics"),
    GENERAL_MEDICINE("General Medicine");

    private final String displayName;

    Specialization(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
