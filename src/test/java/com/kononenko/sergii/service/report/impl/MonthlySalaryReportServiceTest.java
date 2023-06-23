package com.kononenko.sergii.service.report.impl;

import com.kononenko.sergii.model.Employee;
import com.kononenko.sergii.util.SourcePredefinedDataUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MonthlySalaryReportServiceTest {

    Map<String, Employee> testData;

    @BeforeEach
    void init() {
        testData = SourcePredefinedDataUtil.init();
    }

    @AfterEach
    void clear() {
        SourcePredefinedDataUtil.clear();
    }

    @Test
    void getReport() {
        var reportService = new MonthlySalaryReportService();
        var report = reportService.getReport(testData);

        var expectedReportName = "3. Monthly salary report";
        var expectedReportValue = "Month, Total Salary, Total employees\n" +
                "OCTOBER, 7000, 2";
        assertEquals(expectedReportName, report.getName());
        assertEquals(expectedReportValue, report.getValue());
    }
}