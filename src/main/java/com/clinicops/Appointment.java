package com.clinicops;

public class Appointment {
    private String appointmentId;
    private Patient patient;
    private Doctor doctor;
    private String slot;

    public Appointment(String appointmentId, Patient patient, Doctor doctor, String slot) {
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.doctor = doctor;
        this.slot = slot;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getSlot() {
        return slot;
    }

    @Override
    public String toString() {
        return String.format("Appt ID: %s | Patient: %s | Doctor: %s | Specialization: %s | Slot: %s",
                appointmentId, patient.getName(), doctor.getName(), doctor.getSpecialization().getDisplayName(), slot);
    }
}
