package com.clinicops;

public class AdminMenu {
    // Doctor 1
    private static String doc1Name;
    private static String doc1Specialization;
    private static int doc1Experience;
    private static String doc1Shift;

    // Doctor 2
    private static String doc2Name;
    private static String doc2Specialization;
    private static int doc2Experience;
    private static String doc2Shift;

    // Doctor 3
    private static String doc3Name;
    private static String doc3Specialization;
    private static int doc3Experience;
    private static String doc3Shift;

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
        System.out.println("\n--- Enter Doctor 1 Details ---");
        doc1Name = ScannerHelper.readString("Name: ");
        doc1Specialization = ScannerHelper.readString("Specialization: ");
        doc1Experience = ScannerHelper.readInt("Experience (years): ");
        doc1Shift = ScannerHelper.readString("Shift (Morning/Evening/Both): ");

        System.out.println("\n--- Enter Doctor 2 Details ---");
        doc2Name = ScannerHelper.readString("Name: ");
        doc2Specialization = ScannerHelper.readString("Specialization: ");
        doc2Experience = ScannerHelper.readInt("Experience (years): ");
        doc2Shift = ScannerHelper.readString("Shift (Morning/Evening/Both): ");

        System.out.println("\n--- Enter Doctor 3 Details ---");
        doc3Name = ScannerHelper.readString("Name: ");
        doc3Specialization = ScannerHelper.readString("Specialization: ");
        doc3Experience = ScannerHelper.readInt("Experience (years): ");
        doc3Shift = ScannerHelper.readString("Shift (Morning/Evening/Both): ");

        System.out.println("Doctors registered successfully!");
    }

    private void displayDoctors() {
        System.out.println("\n--- Registered Doctors ---");
        if (doc1Name == null && doc2Name == null && doc3Name == null) {
            System.out.println("No doctors registered yet.");
            return;
        }
        
        if (doc1Name != null) {
            System.out.println("Doctor 1: " + doc1Name + ", " + doc1Specialization + ", " + doc1Experience + " years, " + doc1Shift);
        }
        if (doc2Name != null) {
            System.out.println("Doctor 2: " + doc2Name + ", " + doc2Specialization + ", " + doc2Experience + " years, " + doc2Shift);
        }
        if (doc3Name != null) {
            System.out.println("Doctor 3: " + doc3Name + ", " + doc3Specialization + ", " + doc3Experience + " years, " + doc3Shift);
        }
    }
}
