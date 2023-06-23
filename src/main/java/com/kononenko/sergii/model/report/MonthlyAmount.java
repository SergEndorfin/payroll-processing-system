package com.kononenko.sergii.model.report;

public class MonthlyAmount {

    int totalEmployees;
    long totalAmount;

    public MonthlyAmount(int totalEmployees, long totalAmount) {
        this.totalEmployees = totalEmployees;
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return totalAmount + ", " + totalEmployees;
    }
}
