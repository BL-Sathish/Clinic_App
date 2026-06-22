package com.clinicops;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AuditLogger {
    private static final List<String> logs = new ArrayList<>();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void log(String message, String level) {
        String timestamp = LocalDateTime.now().format(formatter);
        String logEntry = String.format("[%s] [%s] %s", timestamp, level.toUpperCase(), message);
        logs.add(logEntry);
    }

    public static void displayLogs() {
        System.out.println("\n--- Audit Logs ---");
        if (logs.isEmpty()) {
            System.out.println("No logs available.");
            return;
        }
        for (String log : logs) {
            System.out.println(log);
        }
    }
}
