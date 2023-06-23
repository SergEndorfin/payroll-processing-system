package com.kononenko.sergii.service.report.impl;

import com.kononenko.sergii.model.Employee;
import com.kononenko.sergii.util.SourcePredefinedDataUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeWiseFinancialReportServiceTest {

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
        var reportService = new EmployeeWiseFinancialReportService();
        var report = reportService.getReport(testData);

        var expectedReportName = "4. Employee wise financial (whole time frame included)";
        var expectedReportValue = "Employee Id, Name, Surname, Total amount paid\n" +
                "emp101, Bill, Gates, 5100\n" +
                "emp103, Martin, Fowler, 0\n" +
                "emp102, Steve Jobs, , 3000";
        assertEquals(expectedReportName, report.getName());
        assertEquals(expectedReportValue, report.getValue());
    }
}