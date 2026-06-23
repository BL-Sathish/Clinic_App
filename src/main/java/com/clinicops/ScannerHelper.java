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
        int attempts = 0;
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.matches(regex)) {
                return input;
            }
            attempts++;
            if (attempts >= 3) {
                AuditLogger.log("Security Warning: Multiple invalid mobile number attempts (" + attempts + ")", "WARNING");
            }
            System.out.println("Invalid mobile number. Must be 10 digits starting with 6, 7, 8, or 9. Please try again.");
        }
    }

    public static String readSlotChoice() {
        String[] slots = {
            "09:00 AM", "09:30 AM", "10:00 AM", "10:30 AM",
            "11:00 AM", "11:30 AM", "12:00 PM", "12:30 PM",
            "04:00 PM", "04:30 PM", "05:00 PM", "05:30 PM",
            "06:00 PM", "06:30 PM", "07:00 PM", "07:30 PM"
        };
        while (true) {
            System.out.println("\nAvailable Slots:");
            for (int i = 0; i < slots.length; i++) {
                System.out.println((i + 1) + ". " + slots[i]);
            }
            int choice = readInt("Select a slot (1-" + slots.length + "): ");
            if (choice >= 1 && choice <= slots.length) {
                return slots[choice - 1];
            }
            System.out.println("Invalid slot choice. Please try again.");
        }
    }

    public static boolean isSlotInShift(String slot, Shift shift) {
        if (shift == Shift.BOTH) return true;
        boolean isMorningSlot = slot.endsWith("AM") || slot.startsWith("12:");
        if (shift == Shift.MORNING && isMorningSlot) return true;
        if (shift == Shift.EVENING && !isMorningSlot) return true;
        return false;
    }
}
