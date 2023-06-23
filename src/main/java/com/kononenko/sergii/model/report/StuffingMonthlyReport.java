package com.kononenko.sergii.model.report;

import com.kononenko.sergii.model.Employee;

import java.util.List;

public record StuffingMonthlyReport(int count, List<Employee> employees) {

    @Override
    public String toString() {
        return count + ", " + employees;
    }
}