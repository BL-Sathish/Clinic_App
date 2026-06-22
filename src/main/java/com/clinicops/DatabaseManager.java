package com.clinicops;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/clinicops";
    private static final String USER = "root";
    private static final String PASSWORD = "Sat123@@";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void initializeDatabase() {
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            String createDoctorsTable = "CREATE TABLE IF NOT EXISTS doctors (" +
                    "id VARCHAR(10) PRIMARY KEY, " +
                    "name VARCHAR(100), " +
                    "specialization VARCHAR(50), " +
                    "experience INT, " +
                    "shift VARCHAR(20))";
            stmt.execute(createDoctorsTable);
            AuditLogger.log("Database initialized and doctors table verified.", "INFO");
        } catch (Exception e) {
            AuditLogger.log("Failed to initialize database: " + e.getMessage(), "ERROR");
            System.out.println("DB Init Error: " + e.getMessage());
        }
    }

    public static void saveDoctors(List<Doctor> doctors) {
        String sql = "INSERT INTO doctors (id, name, specialization, experience, shift) VALUES (?, ?, ?, ?, ?) " +
                     "ON DUPLICATE KEY UPDATE name=VALUES(name), specialization=VALUES(specialization), experience=VALUES(experience), shift=VALUES(shift)";
        
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (Doctor doc : doctors) {
                pstmt.setString(1, doc.getId());
                pstmt.setString(2, doc.getName());
                pstmt.setString(3, doc.getSpecialization().name());
                pstmt.setInt(4, doc.getExperience());
                pstmt.setString(5, doc.getShift().name());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            AuditLogger.log("Saved " + doctors.size() + " doctors to MySQL database.", "INFO");
        } catch (Exception e) {
            AuditLogger.log("Failed to save doctors to DB: " + e.getMessage(), "ERROR");
            System.out.println("DB Save Error: " + e.getMessage());
        }
    }
}
