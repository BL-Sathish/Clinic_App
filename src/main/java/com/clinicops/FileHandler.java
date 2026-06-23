package com.clinicops;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    public static List<Doctor> readDoctorsFromFile(String fileName, int startingIdCounter, List<Doctor> existingDoctors) throws Exception {
        List<Doctor> newDoctors = new ArrayList<>();
        int currentIdCounter = startingIdCounter;

        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            String[] parts;
            // Assume CSV format: Name,Specialization,Experience,Shift
            while ((parts = reader.readNext()) != null) {
                if (parts.length != 4) {
                    System.out.println("Skipping invalid row format: " + String.join(",", parts));
                    continue;
                }

                String name = parts[0].trim();
                Specialization spec = getSpecializationFromString(parts[1].trim());
                int experience;
                try {
                    experience = Integer.parseInt(parts[2].trim());
                } catch (NumberFormatException e) {
                    System.out.println("Skipping row due to invalid experience: " + String.join(",", parts));
                    continue;
                }
                Shift shift = getShiftFromString(parts[3].trim());

                if (spec == null || shift == null) {
                    System.out.println("Skipping row due to misspelled Specialization or Shift: " + String.join(",", parts));
                    continue;
                }

                if (isDuplicate(name, spec, experience, existingDoctors, newDoctors)) {
                    System.out.println("Skipping duplicate doctor: " + name + " - " + spec.getDisplayName());
                    continue;
                }

                String generatedId = String.format("D%04d", currentIdCounter++);
                newDoctors.add(new Doctor(generatedId, name, spec, experience, shift));
            }
        } catch (IOException | CsvValidationException e) {
            throw new Exception("Error reading file: " + e.getMessage());
        }
        return newDoctors;
    }

    private static boolean isDuplicate(String name, Specialization spec, int experience, List<Doctor> existing, List<Doctor> currentBatch) {
        for (Doctor d : existing) {
            if (d.getName().equalsIgnoreCase(name) && d.getSpecialization() == spec && d.getExperience() == experience) {
                return true;
            }
        }
        for (Doctor d : currentBatch) {
            if (d.getName().equalsIgnoreCase(name) && d.getSpecialization() == spec && d.getExperience() == experience) {
                return true;
            }
        }
        return false;
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
