package com.clinicops;

public class FrontDeskMenu {
    public void displayMenu() {
        boolean logout = false;
        while (!logout) {
            System.out.println("\n--- Front Desk Menu ---");
            System.out.println("1. Patient Registration");
            System.out.println("2. Book Appointment");
            System.out.println("3. Logout");

            int choice = ScannerHelper.readInt("Select an option: ");
            switch (choice) {
                case 1:
                    System.out.println("Logic for Patient Registration will be added here.");
                    break;
                case 2:
                    System.out.println("Logic for Book Appointment will be added here.");
                    break;
                case 3:
                    System.out.println("Logging out from Front Desk Menu...");
                    logout = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
