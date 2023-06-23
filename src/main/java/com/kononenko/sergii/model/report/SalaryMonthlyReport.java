package com.kononenko.sergii.model.report;

public class SalaryMonthlyReport {

    private final long totalSalary;
    private final long totalEEmployees;

    public SalaryMonthlyReport(long totalSalary, long totalEEmployees) {
        this.totalSalary = totalSalary;
        this.totalEEmployees = totalEEmployees;
    }

    @Override
    public String toString() {
        return totalSalary + ", " + totalEEmployees;
    }
}
