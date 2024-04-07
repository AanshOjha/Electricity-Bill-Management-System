package com.AanshProject;

public class CalculateBill  {
    private MeterInfo m1;
    private TariffInfo t1;

    public CalculateBill(MeterInfo m1, TariffInfo t1) {
        this.m1 = m1;
        this.t1 = t1;
    }
    public double calculateBill() {
        double bill = (m1.getCurrentReading() - m1.getLastReading())* t1.getTariff();
        return bill;
    }

    public double calculateUnits() {
        double units = (m1.getCurrentReading() - m1.getLastReading());
        return units;
    }

}
