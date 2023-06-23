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

class YearlyFinancialReportServiceTest {

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
        Report<List<Report<String>>> report = new YearlyFinancialReportService().getReport(testData);

        var expectedOuterReportName = "6. Yearly financial report";
        var expectedInnerReportName1 = "2021";
        var expectedInnerReportName2 = "2022";

        var expectedInnerReportValue1 =
                "Event, Emp Id, Event Date, Event value\n" +
                        "ONBOARD, emp101, 2021-10-10, 2021-11-01";
        var expectedInnerReportValue2 =
                "Event, Emp Id, Event Date, Event value\n" +
                        "ONBOARD, emp103, 2022-10-10, 2022-10-15\n" +
                        "ONBOARD, emp102, 2022-10-10, 2022-10-01\n" +
                        "SALARY, emp102, 2022-10-10, 3000\n" +
                        "SALARY, emp101, 2022-10-10, 4000\n" +
                        "BONUS, emp101, 2022-10-10, 1000\n" +
                        "REIMBURSEMENT, emp101, 2022-10-10, 100\n" +
                        "EXIT, emp101, 2022-10-10, 2022-12-01";

        assertEquals(expectedOuterReportName, report.getName());

        report.getValue().forEach(rep -> {
            String name = rep.getName();
            String value = rep.getValue();
            boolean isNameExpected = name.equals(expectedInnerReportName1) || name.equals(expectedInnerReportName2);
            boolean isValueExpected = value.equals(expectedInnerReportValue1) || rep.getValue().equals(expectedInnerReportValue2);
            assertTrue(isNameExpected);
            assertTrue(isValueExpected);
        });
    }
}