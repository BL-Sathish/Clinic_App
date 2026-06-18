package com.clinicops;

public class AdminMenu {
    public void displayMenu() {
        boolean logout = false;
        while (!logout) {
            System.out.println("\n--- Clinic Admin Menu ---");
            System.out.println("1. Doctor's Data Entry");
            System.out.println("2. Bulk Data Entry");
            System.out.println("3. View Audit Logs");
            System.out.println("4. Logout");

            int choice = ScannerHelper.readInt("Select an option: ");
            switch (choice) {
                case 1:
                    System.out.println("Logic for Doctor's Data Entry will be added here.");
                    break;
                case 2:
                    System.out.println("Logic for Bulk Data Entry will be added here.");
                    break;
                case 3:
                    System.out.println("Logic for View Audit Logs will be added here.");
                    break;
                case 4:
                    System.out.println("Logging out from Admin Menu...");
                    logout = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
