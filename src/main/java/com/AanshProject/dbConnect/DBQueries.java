package com.AanshProject.dbConnect;

import com.AanshProject.CalculateBill;
import com.AanshProject.CustomerInfo;
import com.AanshProject.MeterInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBQueries {
    private CustomerInfo c1;
    private MeterInfo m1;
    private CalculateBill cb1;
    public int foundMeterId;

    public DBQueries(CustomerInfo c1, MeterInfo m1, CalculateBill cb1) {
        this.c1 = c1;
        this.cb1 = cb1;
        this.m1 = m1;
    }

    public void getMeterId(int meterIdCheck) throws SQLException{
        String sql = "SELECT meter_id from customer_info where meter_id = ?;";

        try (
             Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            // Assuming m1.meterIdCheck is the value you want to use in the WHERE clause
            stmt.setInt(1, meterIdCheck);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Retrieve the meter ID from the first column of the first row
                    foundMeterId = rs.getInt(1);

                    // Now you can use the foundMeterId variable
                    System.out.println("Found Meter ID: " + foundMeterId);
                    c1.setMeterId(foundMeterId);
                } else {
                    System.out.println("Meter ID not found for the specified condition.");
                }
            } catch (SQLException e) {
                System.err.println("Error inserting data: " + e.getMessage());
                throw e;
            }
        }
    }

    public void getCustInfo(int meterIdCheck) throws SQLException{
        String sql = "SELECT ci.meter_id, name, password, address, email, "
                + "reading_date, last_reading, current_reading, unit_consumed, bill "
                + "from customer_info ci INNER JOIN meter_reading mr ON ci.meter_id = mr.meter_id "
                + "where mr.meter_id = ? order by reading_date desc;";

        try (
                Connection conn = DatabaseConnector.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            // Assuming m1.meterIdCheck is the value you want to use in the WHERE clause
            stmt.setInt(1, meterIdCheck);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Retrieve the meter ID from the first column of the first row
                    c1.setMeterId(rs.getInt(1));
                    c1.setName(rs.getString(2));
                    c1.setAddress(rs.getString(4));
                    c1.setEmail(rs.getString(5));
                    m1.setReadingDate(rs.getDate(6).toString());
                    m1.setLastReading(rs.getDouble(7));
                    m1.setCurrentReading(rs.getDouble(8));
                    cb1.units = (rs.getDouble(9));
                    cb1.bill = (rs.getDouble(10));

                    // Now you can use the foundMeterId variable
                    System.out.println("Found Meter ID: " + meterIdCheck);
                } else {
                    System.out.println("Meter ID not found!!!");
                }
            } catch (SQLException e) {
                System.err.println("Error inserting data: " + e.getMessage());
                throw e;
            }
        }
    }

    public void insertData() throws SQLException {
        String sql = "INSERT INTO meter_reading (meter_id, reading_date, current_reading, last_reading, " +
                "unit_consumed, bill) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set parameters
            stmt.setInt(1, c1.getMeterId());
            stmt.setString(2, m1.getReadingDate().toString());
            stmt.setDouble(3, m1.getCurrentReading());
            stmt.setDouble(4, m1.getLastReading());
            stmt.setDouble(5, cb1.calculateUnits());
            stmt.setDouble(6, cb1.calculateBill());

            // Execute the insert statement
            stmt.executeUpdate();
            System.out.println("User added successfully!");
        } catch (SQLException e) {
            System.err.println("Error inserting data: " + e.getMessage());
            throw e;
        }
}

        public void registerUser() throws SQLException {
        String sql = "INSERT INTO customer_info (name, meter_id, address, email, password) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            System.out.println(c1.getMeterId());
            // Set parameters
            stmt.setString(1, c1.getName());
            stmt.setInt(2, c1.getMeterId());
            stmt.setString(3, c1.getAddress());
            stmt.setString(4, c1.getEmail());
            stmt.setString(5, c1.getPassword());

            // Execute the insert statement
            stmt.executeUpdate();
            System.out.println("Data inserted successfully!");
        } catch (SQLException e) {
            System.err.println("Error inserting data: " + e.getMessage());
            throw e;
        }
    }
}
