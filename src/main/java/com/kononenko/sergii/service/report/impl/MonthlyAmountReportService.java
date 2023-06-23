package com.kononenko.sergii.service.report.impl;

import com.kononenko.sergii.model.Employee;
import com.kononenko.sergii.model.event.Event;
import com.kononenko.sergii.model.event.sub.payment.PaymentEvent;
import com.kononenko.sergii.model.report.MonthlyAmount;
import com.kononenko.sergii.model.report.Report;
import com.kononenko.sergii.service.report.ReportFormatterService;
import com.kononenko.sergii.service.report.ReportService;

import java.util.Map;
import java.util.stream.Collectors;

public class MonthlyAmountReportService implements ReportService, ReportFormatterService {

    private static final String CURRENT_REPORT_FILE_NAME = "5. Monthly amount";
    private static final String CURRENT_REPORT_HEADERS = "Month, Total Amount (Salary + Bonus + REIMBURSEMENT), Total employees\n";

    @Override
    public Report<String> getReport(Map<String, Employee> dataSource) {
        return new Report<>(
                CURRENT_REPORT_FILE_NAME,
                CURRENT_REPORT_HEADERS + this.format(
                        dataSource
                                .values()
                                .stream()
                                .flatMap(employee -> employee.getEvents().stream())
                                .filter(PaymentEvent.class::isInstance)
                                .map(PaymentEvent.class::cast)
                                .collect(Collectors.groupingBy(
                                        event -> event
                                                .getEventDate()
                                                .getMonth(),
                                        Collectors.groupingBy(
                                                Event::getEmpId,
                                                Collectors.summingLong(PaymentEvent::getAmountInUsd)
                                        )
                                ))
                                .entrySet()
                                .stream()
                                .collect(Collectors.toMap(
                                        Map.Entry::getKey, // month as a key
                                        monthMapEntry -> new MonthlyAmount(
                                                monthMapEntry.getValue().size(),
                                                monthMapEntry
                                                        .getValue()
                                                        .values()
                                                        .stream()
                                                        .mapToLong(aLong -> aLong)
                                                        .sum()
                                        )
                                ))
                )
        );
    }
}
