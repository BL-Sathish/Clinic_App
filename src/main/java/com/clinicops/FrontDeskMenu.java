package com.clinicops;

import java.util.ArrayList;
import java.util.List;

public class FrontDeskMenu {
    private static final List<Patient> patientList = new ArrayList<>();
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
                    System.out.println("Logic for Book Appointment will be added here.");
                    break;
                case 3:
                    System.out.println("Logic for View Appointments will be added here.");
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

        String generatedId = String.format("P%04d", patientIdCounter++);
        Patient newPatient = new Patient(generatedId, name, gender, age, mobileNumber);
        patientList.add(newPatient);

        System.out.println("Patient registered successfully with ID: " + generatedId);
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
