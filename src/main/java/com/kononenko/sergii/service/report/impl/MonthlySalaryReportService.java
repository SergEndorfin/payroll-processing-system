package com.kononenko.sergii.service.report.impl;

import com.kononenko.sergii.model.Employee;
import com.kononenko.sergii.model.event.sub.payment.PaymentEvent;
import com.kononenko.sergii.model.event.sub.payment.Salary;
import com.kononenko.sergii.model.report.Report;
import com.kononenko.sergii.model.report.SalaryMonthlyReport;
import com.kononenko.sergii.service.report.ReportFormatterService;
import com.kononenko.sergii.service.report.ReportService;

import java.time.Month;
import java.util.Map;
import java.util.stream.Collectors;

public class MonthlySalaryReportService implements ReportService, ReportFormatterService {

    public static final String CURRENT_REPORT_FILE_NAME = "3. Monthly salary report";
    public static final String CURRENT_REPORT_HEADERS = "Month, Total Salary, Total employees\n";

    @Override
    public Report<String> getReport(Map<String, Employee> dataSource) {
        Map<Month, SalaryMonthlyReport> report = dataSource
                .values()
                .stream()
                .flatMap(employee -> employee.getEvents().stream())
                .filter(Salary.class::isInstance)
                .collect(Collectors.groupingBy(event -> event.getEventDate().getMonth()))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        monthListEntry -> {
                            var events = monthListEntry.getValue();
                            return new SalaryMonthlyReport(
                                    events.stream()
                                            .mapToLong(event -> ((PaymentEvent) event).getAmountInUsd())
                                            .sum(),
                                    events.size()
                            );
                        }
                ));
        return new Report<>(
                CURRENT_REPORT_FILE_NAME,
                CURRENT_REPORT_HEADERS + this.format(report)
        );
    }
}
