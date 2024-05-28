package com.AanshProject;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Scanner;

import com.AanshProject.dbConnect.DBQueries;

public class App  {
    public static void main(String[] args) throws SQLException, FileNotFoundException {
        CustomerInfo c1 = new CustomerInfo();
        Scanner sc = new Scanner(System.in);
        MeterInfo m1 = new MeterInfo(c1);
        TariffInfo t1 = new TariffInfo();
        CalculateBill cb1 = new CalculateBill(m1, t1);
        DBQueries db1 = new DBQueries(c1, m1, cb1);
        PDFGenerator pdf = new PDFGenerator(c1, m1, cb1, t1);

        db1.createResources();

        System.out.println("Hello, What would you like to do?");
        int input;
        while (true) {
            System.out.println("""
            1. Register user
            2. Insert Meter Info
            3. Generate Bill
            0. Exit
            """);
            input = sc.nextInt();
            if (input==1) {
                // Register User
                c1.registerUser();
                db1.registerUser();
            }

            if (input==2) {
                // Take current and last month readings
                System.out.println("Enter meter ID to confirm its you");
                int meterIdCheck = sc.nextInt();
                db1.getMeterId(meterIdCheck);
                if (db1.foundMeterId != meterIdCheck)
                    continue;
                m1.inputReading();
                db1.insertData();
            }

            if (input==3) {
                // Print Electricity Bill
                System.out.println("Enter meter id:");
                int meterIdCheck = sc.nextInt();
                db1.getCustInfo(meterIdCheck);
                if (c1.getMeterId() != meterIdCheck)
                    continue;

                pdf.statement();
            }
            if (input==0) {
                break;
            }
        }
    }
}