package com.kononenko.sergii.service.report.impl;

import com.kononenko.sergii.model.Employee;
import com.kononenko.sergii.model.report.Report;
import com.kononenko.sergii.service.report.ReportService;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AllEmployeesReportServiceTest {

    @Test
    void getReport() {
        var reportService = new AllEmployeesReportService();
        var report = reportService.getReport(Map.of("empId", new Employee("id")));
        var expectedReportName = "1. Total number of employees in an organization";
        var expectedReportValue = "Total employee\n1";
        assertEquals(expectedReportName, report.getName());
        assertEquals(expectedReportValue, report.getValue());
    }
}