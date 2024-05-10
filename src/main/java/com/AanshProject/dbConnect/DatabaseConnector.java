package com.AanshProject.dbConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector {

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/";
        String username = "root";
        String password = "password";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement()) {
            // Create the database
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS Elec_Bill");
            System.out.println("Database initialised successfully.");
        } catch (SQLException e) {
            System.err.println("Error fetching data: " + e.getMessage());
            throw e;
        }

        url = "jdbc:mysql://localhost:3306/Elec_Bill";

        return DriverManager.getConnection(url, username, password);
    }
}
