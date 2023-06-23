package com.kononenko.sergii.service.report.impl;

import com.kononenko.sergii.model.Employee;
import com.kononenko.sergii.model.event.Event;
import com.kononenko.sergii.model.report.Report;
import com.kononenko.sergii.service.report.ReportService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class YearlyFinancialReportService implements ReportService {

    public static final String CURRENT_REPORT_FILE_NAME = "6. Yearly financial report";
    private static final String CURRENT_REPORT_HEADERS = "Event, Emp Id, Event Date, Event value\n";

    @Override
    public Report<List<Report<String>>> getReport(Map<String, Employee> dataSource) {
        Map<Integer, List<Event>> report = dataSource
                .values()
                .stream()
                .flatMap(employee -> employee.getEvents().stream())
                .collect(Collectors.groupingBy(
                        event -> event.getEventDate().getYear()
                ));
        return new Report<>(
                CURRENT_REPORT_FILE_NAME,
                this.splitReportsGranularly(report)
        );
    }

    private List<Report<String>> splitReportsGranularly(Map<Integer, List<Event>> report) {
        return report.entrySet()
                .stream()
                .map(entry -> new Report<>(
                                entry.getKey().toString(),
                                CURRENT_REPORT_HEADERS + entry.getValue()
                                        .stream()
                                        .map(Event::toString)
                                        .collect(Collectors.joining("\n"))
                        )
                )
                .toList();
    }
}