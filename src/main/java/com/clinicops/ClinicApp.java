package com.clinicops;

public class ClinicApp {
    private static final int ROLE_ADMIN = 1;
    private static final int ROLE_FRONT_DESK = 2;
    private static final int ROLE_EXIT = 3;

    public static void main(String[] args) {
        System.out.println("Welcome to ClinicOps App!");
        DatabaseManager.initializeDatabase();
        AdminMenu adminMenu = new AdminMenu();
        FrontDeskMenu frontDeskMenu = new FrontDeskMenu();

        boolean exitSystem = false;
        while (!exitSystem) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Clinic Admin");
            System.out.println("2. Front Desk Executive");
            System.out.println("3. Exit");

            int roleChoice = ScannerHelper.readInt("Select your persona: ");

            switch (roleChoice) {
                case ROLE_ADMIN:
                    adminMenu.displayMenu();
                    break;
                case ROLE_FRONT_DESK:
                    frontDeskMenu.displayMenu();
                    break;
                case ROLE_EXIT:
                    System.out.println("Saving records to database...");
                    DatabaseManager.saveDoctors(AdminMenu.getDoctorList());
                    System.out.println("Exiting application. Goodbye!");
                    exitSystem = true;
                    break;
                default:
                    System.out.println("Invalid option. Please choose 1, 2, or 3.");
            }
        }
    }
}
