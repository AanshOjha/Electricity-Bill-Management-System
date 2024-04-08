package com.AanshProject;

import com.AanshProject.dbConnect.DBQueries;

import java.sql.SQLException;
import java.util.Scanner;

public class App  {
    private CustomerInfo c1;
    private MeterInfo m1;
    private CalculateBill cb1;
    private StatementGenerate sg;
    private TariffInfo t1;
    private DBQueries db1;

    public App(CustomerInfo c1, MeterInfo m1, CalculateBill cb1, StatementGenerate sg, TariffInfo t1, DBQueries db1) {
        this.c1 = c1;
        this.m1 = m1;
        this.cb1 = cb1;
        this.sg = sg;
        this.t1 = t1;
        this.db1 = db1;
    }
    public static void main(String[] args) throws SQLException {
        CustomerInfo c1 = new CustomerInfo();
        Scanner sc = new Scanner(System.in);
        MeterInfo m1 = new MeterInfo(c1);
        TariffInfo t1 = new TariffInfo();
        CalculateBill cb1 = new CalculateBill(m1, t1);
        StatementGenerate sg = new StatementGenerate(c1, m1, cb1, t1);
        DBQueries db1 = new DBQueries(c1, m1, cb1);

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
                db1.registerUser(c1.getName(), c1.getMeterId(), c1.getAddress(), c1.getEmail(), c1.getPassword());
                System.out.println("User added successfully!");
            }

            if (input==2) {
                // Take current and last month readings
                System.out.println("Enter meter ID to confirm its you");
                int meterIdCheck = sc.nextInt();
                db1.getMeterId(meterIdCheck);
                if (db1.foundMeterId != meterIdCheck)
                    break;
                m1.inputReading();
                db1.insertData();
                System.out.println("Info added successfully!");
            }

            if (input==3) {
                // Print Electricity Bill
                System.out.println(sg.statement());
            }
            if (input==0) {
                break;
            }
        }
    }
}