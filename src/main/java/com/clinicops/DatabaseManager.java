package com.clinicops;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/clinicops";
    private static final String USER = "root";
    private static final String PASSWORD = "Sat123@@";

    private static EntityManagerFactory emf;

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void initializeDatabase() {
        try {
            emf = Persistence.createEntityManagerFactory("ClinicOpsPU");
            AuditLogger.log("JPA EntityManagerFactory initialized successfully.", "INFO");
        } catch (Exception e) {
            AuditLogger.log("Failed to initialize JPA: " + e.getMessage(), "ERROR");
            System.out.println("DB Init Error: " + e.getMessage());
        }
    }

    public static void saveDoctors(List<Doctor> doctors) {
        if (emf == null) return;
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            for (Doctor doc : doctors) {
                em.merge(doc);
            }
            em.getTransaction().commit();
            AuditLogger.log("Saved " + doctors.size() + " doctors using JPA Hibernate.", "INFO");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            AuditLogger.log("Failed to save doctors via JPA: " + e.getMessage(), "ERROR");
            System.out.println("JPA Save Error: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}
