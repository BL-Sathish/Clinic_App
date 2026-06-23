package com.clinicops;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FrontDeskMenu {
    private static final List<Patient> patientList = new ArrayList<>();
    private static final List<Appointment> appointmentList = new ArrayList<>();
    private static int appointmentIdCounter = 1;
    private static final Random random = new Random();
    private static int patientIdCounter = 1;

    public void displayMenu() {
        boolean logout = false;
        while (!logout) {
            displayFrontDeskOptions();
            int choice = ScannerHelper.readInt("Select an option: ");
            switch (choice) {
                case 1:
                    registerPatient();
                    break;
                case 2:
                    bookAppointment();
                    break;
                case 3:
                    viewAppointments();
                    break;
                case 4:
                    System.out.println("Logic for Update Patient Data will be added here.");
                    break;
                case 5:
                    displayPatients();
                    break;
                case 6:
                    System.out.println("Logging out from Front Desk Menu...");
                    logout = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void displayFrontDeskOptions() {
        System.out.println("\n--- Clinic Front Desk Menu ---");
        System.out.println("1. Register Patient Data");
        System.out.println("2. Book Appointment");
        System.out.println("3. View Appointments");
        System.out.println("4. Update Patient Data");
        System.out.println("5. Display Patients");
        System.out.println("6. Logout");
    }

    private void registerPatient() {
        System.out.println("\n--- Register New Patient ---");
        String name = ScannerHelper.readString("Name: ");
        String gender = ScannerHelper.readString("Gender (M/F/Other): ");
        int age = ScannerHelper.readInt("Age: ");
        String mobileNumber = ScannerHelper.readMobileNumber("Mobile Number: ");

        for (Patient p : patientList) {
            if (p.getMobileNumber().equals(mobileNumber)) {
                System.out.println("Patient is already registered with mobile number: " + mobileNumber);
                return;
            }
        }

        String generatedId = String.format("P%04d", patientIdCounter++);
        Patient newPatient = new Patient(generatedId, name, gender, age, mobileNumber);
        patientList.add(newPatient);

        System.out.println("Patient registered successfully with ID: " + generatedId);
    }

    private void bookAppointment() {
        System.out.println("\n--- Book Appointment ---");
        String mobileNumber = ScannerHelper.readMobileNumber("Enter Patient Mobile Number: ");
        Patient patient = null;
        for (Patient p : patientList) {
            if (p.getMobileNumber().equals(mobileNumber)) {
                patient = p;
                break;
            }
        }

        if (patient == null) {
            System.out.println("Patient not found. Please register the patient first.");
            return;
        }

        System.out.println("Booking appointment for: " + patient.getName());
        Specialization requiredSpecialization = ScannerHelper.readEnumChoice("\nSelect required Specialization:", Specialization.class);
        String slot = ScannerHelper.readSlotChoice();

        List<Doctor> allDoctors = AdminMenu.getDoctorList();
        if (allDoctors.isEmpty()) {
            System.out.println("No doctors available in the clinic.");
            return;
        }

        List<Doctor> availableDoctors = new ArrayList<>();
        for (Doctor doctor : allDoctors) {
            if (doctor.getSpecialization() == requiredSpecialization && doctor.isSlotAvailable(slot) && ScannerHelper.isSlotInShift(slot, doctor.getShift())) {
                availableDoctors.add(doctor);
            }
        }

        if (availableDoctors.isEmpty()) {
            System.out.println("No doctors are available for the selected slot.");
            return;
        }

        // Randomly assign an available doctor
        Doctor assignedDoctor = availableDoctors.get(random.nextInt(availableDoctors.size()));
        assignedDoctor.bookSlot(slot);

        String apptId = String.format("A%04d", appointmentIdCounter++);
        Appointment appointment = new Appointment(apptId, patient, assignedDoctor, slot);
        appointmentList.add(appointment);

        System.out.println("Appointment booked successfully!");
        System.out.println(appointment);
    }

    private void viewAppointments() {
        System.out.println("\n--- Scheduled Appointments ---");
        if (appointmentList.isEmpty()) {
            System.out.println("No appointments scheduled yet.");
        } else {
            for (Appointment appt : appointmentList) {
                System.out.println(appt);
            }
        }
    }

    private void displayPatients() {
        System.out.println("\n--- Registered Patients ---");
        if (patientList.isEmpty()) {
            System.out.println("No patients registered yet.");
        } else {
            for (Patient patient : patientList) {
                System.out.println(patient);
            }
        }
    }
}
