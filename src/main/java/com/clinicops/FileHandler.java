package com.clinicops;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    public static List<Doctor> readDoctorsFromFile(String fileName, int startingIdCounter) throws Exception {
        List<Doctor> newDoctors = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int currentIdCounter = startingIdCounter;
            // Assume CSV format: Name,Specialization,Experience,Shift
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length != 4) {
                    throw new Exception("Invalid row format: " + line);
                }

                String name = parts[0].trim();
                Specialization spec = getSpecializationFromString(parts[1].trim());
                int experience = Integer.parseInt(parts[2].trim());
                Shift shift = getShiftFromString(parts[3].trim());

                if (spec == null || shift == null) {
                    throw new Exception("Invalid Specialization or Shift in row: " + line);
                }

                String generatedId = String.format("D%04d", currentIdCounter++);
                newDoctors.add(new Doctor(generatedId, name, spec, experience, shift));
            }
        } catch (IOException e) {
            throw new Exception("Error reading file: " + e.getMessage());
        }
        return newDoctors;
    }

    private static Specialization getSpecializationFromString(String value) {
        for (Specialization s : Specialization.values()) {
            if (s.getDisplayName().equalsIgnoreCase(value) || s.name().equalsIgnoreCase(value)) {
                return s;
            }
        }
        return null;
    }

    private static Shift getShiftFromString(String value) {
        for (Shift s : Shift.values()) {
            if (s.getDisplayName().equalsIgnoreCase(value) || s.name().equalsIgnoreCase(value)) {
                return s;
            }
        }
        return null;
    }
}
