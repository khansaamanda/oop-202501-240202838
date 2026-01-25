package com.upb.agripos.config;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    
    private DatabaseConnection() {
        // Private constructor untuk Singleton
    }
    
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
    
    public void connect() {
        System.out.println("Database connected successfully");
    }
}