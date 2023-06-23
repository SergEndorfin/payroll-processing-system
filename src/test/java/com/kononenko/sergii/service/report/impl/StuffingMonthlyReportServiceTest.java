package com.kononenko.sergii.service.report.impl;

import com.kononenko.sergii.model.Employee;
import com.kononenko.sergii.model.report.Report;
import com.kononenko.sergii.util.SourcePredefinedDataUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StuffingMonthlyReportServiceTest {

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
        Report<List<Report<String>>> report = new StuffingMonthlyReportService().getReport(testData);

        var expectedOuterReportName = "2. Month wise following details";
        var expectedInnerReportName1 = "EXITED";
        var expectedInnerReportName2 = "JOINED";

        var expectedInnerReportValue1 =
                "Month, Total employees, EmpId, Designation, Name, Surname\n" +
                        "DECEMBER, 1, emp101, Software Engineer, Bill, Gates";
        var expectedInnerReportValue2 =
                "Month, Total employees, EmpId, Designation, Name, Surname\n" +
                        "OCTOBER, 2, emp103, Software Engineer, Martin, Fowler\n" +
                        "OCTOBER, 2, emp102, Architect, Steve Jobs, EMPTY\n" +
                        "NOVEMBER, 1, emp101, Software Engineer, Bill, Gates";

        assertEquals(expectedOuterReportName, report.getName());

        report.getValue().forEach(rep -> {
            String name = rep.getName();
            String value = rep.getValue();
            boolean isNameExpected = name.equals(expectedInnerReportName1) || name.equals(expectedInnerReportName2);
            boolean isValueExpected = value.equals(expectedInnerReportValue1) || value.equals(expectedInnerReportValue2);
            assertTrue(isNameExpected);
            assertTrue(isValueExpected);
        });
    }
}