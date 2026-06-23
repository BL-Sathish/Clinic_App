package com.clinicops;

import java.util.ArrayList;
import java.util.List;

public class AdminMenu {
    private static final List<Doctor> doctorList = new ArrayList<>();
    private static int idCounter = 1;

    public void displayMenu() {
        boolean logout = false;
        while (!logout) {
            displayAdminOptions();
            int choice = ScannerHelper.readInt("Select an option: ");
            switch (choice) {
                case 1:
                    registerDoctors();
                    break;
                case 2:
                    System.out.println("Logic for Bulk Data Entry will be added here.");
                    break;
                case 3:
                    System.out.println("Logic for View Audit Logs will be added here.");
                    break;
                case 4:
                    displayDoctors();
                    break;
                case 5:
                    System.out.println("Logging out from Admin Menu...");
                    logout = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void displayAdminOptions() {
        System.out.println("\n--- Clinic Admin Menu ---");
        System.out.println("1. Doctor's Data Entry");
        System.out.println("2. Bulk Data Entry");
        System.out.println("3. View Audit Logs");
        System.out.println("4. Display Doctor's List");
        System.out.println("5. Logout");
    }

    private void registerDoctors() {
        boolean addMore = true;
        while (addMore) {
            System.out.println("\n--- Enter Doctor Details ---");
            String name = ScannerHelper.readString("Name: ");
            String specialization = ScannerHelper.readString("Specialization: ");
            int experience = ScannerHelper.readInt("Experience (years): ");
            String shift = ScannerHelper.readString("Shift (Morning/Evening/Both): ");

            String generatedId = String.format("D%04d", idCounter++);
            Doctor newDoctor = new Doctor(generatedId, name, specialization, experience, shift);
            doctorList.add(newDoctor);

            System.out.println("Doctor registered successfully with ID: " + generatedId);

            String choice = ScannerHelper.readString("Do you want to add another doctor? (y/n): ");
            if (!choice.equalsIgnoreCase("y")) {
                addMore = false;
            }
        }
    }

    private void displayDoctors() {
        System.out.println("\n--- Registered Doctors ---");
        if (doctorList.isEmpty()) {
            System.out.println("No doctors registered yet.");
        } else {
            for (Doctor doctor : doctorList) {
                System.out.println(doctor);
            }
        }
    }
}
