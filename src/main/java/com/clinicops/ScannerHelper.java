package com.clinicops;

import java.util.Scanner;

public class ScannerHelper {
    private static final Scanner scanner = new Scanner(System.in);

    public static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    public static String readString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Input cannot be empty. Please try again.");
        }
    }

    public static <T extends Enum<T>> T readEnumChoice(String prompt, Class<T> enumClass) {
        T[] values = enumClass.getEnumConstants();
        while (true) {
            System.out.println(prompt);
            for (int i = 0; i < values.length; i++) {
                System.out.println((i + 1) + ". " + values[i].name());
            }
            int choice = readInt("Select option (1-" + values.length + "): ");
            if (choice >= 1 && choice <= values.length) {
                return values[choice - 1];
            }
            System.out.println("Invalid choice. Please try again.");
        }
    }

    public static String readMobileNumber(String prompt) {
        // Indian mobile number validation regex: Starts with 6, 7, 8, or 9 and exactly 10 digits.
        String regex = "^[6-9]\\d{9}$";
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.matches(regex)) {
                return input;
            }
            System.out.println("Invalid mobile number. Must be 10 digits starting with 6, 7, 8, or 9. Please try again.");
        }
    }
}
