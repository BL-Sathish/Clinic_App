package com.clinicops;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "doctors")
public class Doctor {
    @Id
    private String id;
    
    @Transient
    private List<String> bookedSlots = new ArrayList<>();
    
    private String name;
    
    @Enumerated(EnumType.STRING)
    private Specialization specialization;
    
    private int experience;
    
    @Enumerated(EnumType.STRING)
    private Shift shift;

    public Doctor() {}


    public Doctor(String id, String name, Specialization specialization, int experience, Shift shift) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.experience = experience;
        this.shift = shift;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public int getExperience() {
        return experience;
    }

    public Shift getShift() {
        return shift;
    }

    public boolean isSlotAvailable(String slot) {
        return !bookedSlots.contains(slot);
    }

    public void bookSlot(String slot) {
        bookedSlots.add(slot);
    }

    @Override
    public String toString() {
        return String.format("ID: %s | Name: %s | Specialization: %s | Experience: %d years | Shift: %s",
                id, name, specialization.getDisplayName(), experience, shift.getDisplayName());
    }
}
