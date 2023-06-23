package com.kononenko.sergii.service.report.impl;

import com.kononenko.sergii.model.Employee;
import com.kononenko.sergii.model.event.sub.payment.PaymentEvent;
import com.kononenko.sergii.model.report.Report;
import com.kononenko.sergii.service.report.ReportFormatterService;
import com.kononenko.sergii.service.report.ReportService;

import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeWiseFinancialReportService implements ReportService, ReportFormatterService {

    private static final String CURRENT_REPORT_FILE_NAME = "4. Employee wise financial (whole time frame included)";
    private static final String CURRENT_REPORT_HEADERS = "Employee Id, Name, Surname, Total amount paid\n";

    @Override
    public Report<String> getReport(Map<String, Employee> dataSource) {
        Map<String, Long> report = dataSource
                .values()
                .stream()
                .collect(Collectors.groupingBy(
                        this::formatEmployeeData,
                        Collectors.summingLong(employee ->
                                employee.getEvents()
                                        .stream()
                                        .filter(PaymentEvent.class::isInstance)
                                        .mapToLong(event -> ((PaymentEvent) event).getAmountInUsd())
                                        .sum()
                        )
                ));
        return new Report<>(
                CURRENT_REPORT_FILE_NAME,
                CURRENT_REPORT_HEADERS + this.format(report)
        );
    }

    private String formatEmployeeData(Employee employee) {
        String empLName = employee.getEmpLName();
        return employee.getEmpID() + ", " +
                employee.getEmpFName() + ", " +
                (null != empLName ? empLName : "");
    }
}
