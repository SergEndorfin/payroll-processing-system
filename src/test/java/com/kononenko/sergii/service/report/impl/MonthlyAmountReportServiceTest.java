package com.kononenko.sergii.service.report.impl;

import com.kononenko.sergii.model.Employee;
import com.kononenko.sergii.util.SourcePredefinedDataUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MonthlyAmountReportServiceTest {

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
        var report = new MonthlyAmountReportService().getReport(testData);

        var expectedReportName = "5. Monthly amount";
        var expectedReportValue = "Month, Total Amount (Salary + Bonus + REIMBURSEMENT), Total employees\n" +
                "OCTOBER, 8100, 2";
        assertEquals(expectedReportName, report.getName());
        assertEquals(expectedReportValue, report.getValue());
    }
}