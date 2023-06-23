package com.kononenko.sergii.service.report.impl;

import com.kononenko.sergii.model.Employee;
import com.kononenko.sergii.model.report.Report;
import com.kononenko.sergii.service.report.ReportService;

import java.util.Map;

public class AllEmployeesReportService implements ReportService {

    private static final String CURRENT_REPORT_FILE_NAME = "1. Total number of employees in an organization";
    private static final String CURRENT_REPORT_HEADERS = "Total employee\n";

    @Override
    public Report<String> getReport(Map<String, Employee> dataSource) {
        return new Report<>(
                CURRENT_REPORT_FILE_NAME,
                CURRENT_REPORT_HEADERS + dataSource.size()
        );
    }
}