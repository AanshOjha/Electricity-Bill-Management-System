package com.AanshProject;

public class CalculateBill  {
    private MeterInfo m1;
    private TariffInfo t1;
    public double bill;
    public double units;
    public double gst;
    public double payable;

    public CalculateBill(MeterInfo m1, TariffInfo t1) {
        this.m1 = m1;
        this.t1 = t1;
    }
    public double calculateBill() {
        bill = (m1.getCurrentReading() - m1.getLastReading())* t1.getTariff();
        return bill;
    }

    public double calculateUnits() {
        units = (m1.getCurrentReading() - m1.getLastReading());
        return units;
    }

    public double calculateGST() {
        gst = (t1.getGstRate()/100)*calculateBill();
        return gst;
    }

    public double payableAmount() {
        payable = calculateGST() + calculateBill();
        return payable;
    }

}
