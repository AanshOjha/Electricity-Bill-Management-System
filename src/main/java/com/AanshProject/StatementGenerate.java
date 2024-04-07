package com.AanshProject;

public class StatementGenerate {
    private CustomerInfo c1;
    private MeterInfo m1;
    private CalculateBill cb1;
    private TariffInfo t1;

    public StatementGenerate(CustomerInfo c1, MeterInfo m1, CalculateBill cb1, TariffInfo t1) {
        this.c1 = c1;
        this.m1 = m1;
        this.cb1 = cb1;
        this.t1 = t1;
    }

    public String statement() {
        String ans = String.format("""
                Name: %s
                Email: %s
                Meter ID: %d
                Address: %s
                Current Month Reading: %.2f
                Last Month Reading: %.2f
                Units consumed: %.2f
                Bill: %.2f
                """, c1.getName(), c1.getEmail(), c1.getMeterId(), c1.getAddress(), m1.getCurrentReading(), m1.getLastReading(), cb1.calculateUnits(), cb1.calculateBill());
        return ans;
    }
}

