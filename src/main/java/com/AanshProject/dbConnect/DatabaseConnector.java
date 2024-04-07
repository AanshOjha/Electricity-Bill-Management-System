package com.AanshProject.dbConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/elec_bill";
        String username = "root";
        String password = "password";

        return DriverManager.getConnection(url, username, password);
    }
}
