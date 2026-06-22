package com.clinicops;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class AuditLogger {
    private static final Logger logger = LogManager.getLogger(AuditLogger.class);
    private static final String LOG_FILE = "clinicops.log";

    public static void log(String message, String level) {
        switch (level.toUpperCase()) {
            case "INFO":
                logger.info(message);
                break;
            case "WARNING":
                logger.warn(message);
                break;
            case "ERROR":
                logger.error(message);
                break;
            default:
                logger.debug(message);
                break;
        }
    }

    public static void displayLogs() {
        System.out.println("\n--- Audit Logs ---");
        try {
            Path logPath = Paths.get(LOG_FILE);
            if (Files.exists(logPath)) {
                List<String> lines = Files.readAllLines(logPath);
                if (lines.isEmpty()) {
                    System.out.println("No logs available.");
                } else {
                    for (String line : lines) {
                        System.out.println(line);
                    }
                }
            } else {
                System.out.println("No logs available yet.");
            }
        } catch (Exception e) {
            System.out.println("Could not read log file: " + e.getMessage());
        }
    }
}
