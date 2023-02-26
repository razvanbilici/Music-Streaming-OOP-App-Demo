package com.company.dbconfig;

import java.sql.SQLException;

import java.sql.Connection;
import java.sql.DriverManager;


public class DbConfig {

    private static final String DB_URL = "jdbc:mysql://127.0.0.1/mss";
    private static final String USER = "root";
    private static final String PASSWORD = "mihai#12";
    private static Connection databaseConnection;

    private DbConfig() {
    }

    public static Connection getDatabaseConnection() {
        try {
            if (databaseConnection == null || databaseConnection.isClosed()) {
                databaseConnection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return databaseConnection;
    }

    public static void closeDatabaseConnection() {
        try {
            if (databaseConnection != null && !databaseConnection.isClosed()) {
                databaseConnection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}