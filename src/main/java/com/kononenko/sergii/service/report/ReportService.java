package com.kononenko.sergii.service.report;

import com.kononenko.sergii.model.Employee;
import com.kononenko.sergii.model.report.Report;

import java.util.Map;

public interface ReportService {

    Report getReport(Map<String, Employee> dataSource);
}