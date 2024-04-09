package com.AanshProject;

import java.sql.Date;
import java.util.Scanner;

public class MeterInfo {
    private java.sql.Date readingDate;
    private double lastReading;
    private double currentReading;

    public MeterInfo(CustomerInfo c1) {
        this.c1 = c1;
    }

    private CustomerInfo c1;

    public void inputReading() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter current reading");
        setCurrentReading(sc.nextDouble());
        System.out.println("Enter last reading");
        sc.nextLine();
        setLastReading(sc.nextDouble());
        System.out.println("Enter Reading Date");
        setReadingDate(sc.next());

    }

    public Date getReadingDate() {
        return readingDate;
    }

    public void setReadingDate(String readingDate) {
        this.readingDate = Date.valueOf(readingDate);
    }

    public double getLastReading() {
        return lastReading;
    }

    public void setLastReading(double lastReading) {
        this.lastReading = lastReading;
    }

    public double getCurrentReading() {
        return currentReading;
    }

    public void setCurrentReading(double currentReading) {
        this.currentReading = currentReading;
    }
}
