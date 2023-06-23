package com.kononenko.sergii.service.report.impl;

import com.kononenko.sergii.model.Employee;
import com.kononenko.sergii.model.event.Event;
import com.kononenko.sergii.model.event.sub.stuffing.Onboard;
import com.kononenko.sergii.model.event.sub.stuffing.StaffingEvent;
import com.kononenko.sergii.model.report.Report;
import com.kononenko.sergii.model.report.StuffingMonthlyReport;
import com.kononenko.sergii.service.report.ReportService;
import com.kononenko.sergii.service.report.StuffingMonthlyReportFormatterService;

import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class StuffingMonthlyReportService implements ReportService, StuffingMonthlyReportFormatterService {

    private static final String JOINED_EMPLOYEES = "JOINED";
    private static final String EXITED_EMPLOYEES = "EXITED";
    public static final String CURRENT_REPORT_FILE_NAME = "2. Month wise following details";
    public static final String CURRENT_REPORT_HEADERS = "Month, Total employees, EmpId, Designation, Name, Surname\n";

    @Override
    public Report<List<Report<String>>> getReport(Map<String, Employee> dataSource) {
        Map<String, Map<Month, List<Event>>> samplingByJoinedAndExitedEvents =
                splitByStuffingEventTypeOnlyAndByMonthEachType(dataSource);

        Map<String, Map<Month, StuffingMonthlyReport>> samplingByJoinedAndExitedEmployeesByMonth =
                collectEmployeesInfoBySamplingEvents(dataSource, samplingByJoinedAndExitedEvents);

        List<Report<String>> reportsBySampling =
                generateStuffingReports(samplingByJoinedAndExitedEmployeesByMonth);

        return new Report<>(CURRENT_REPORT_FILE_NAME, reportsBySampling);
    }

    private static Map<String, Map<Month, List<Event>>> splitByStuffingEventTypeOnlyAndByMonthEachType(
            Map<String, Employee> dataSource
    ) {
        return dataSource
                .values()
                .stream()
                .flatMap(employee -> employee.getEvents().stream())
                .filter(StaffingEvent.class::isInstance)
                .collect(Collectors.groupingBy(
                        getTypeOfStuffingEvent(),
                        groupEventsByMonth()
                ));
    }

    private static Collector<Event, ?, Map<Month, List<Event>>> groupEventsByMonth() {
        return Collectors.groupingBy(
                event -> ((StaffingEvent) event).getStaffingEventValue().getMonth()
        );
    }

    private static Function<Event, String> getTypeOfStuffingEvent() {
        return event -> event instanceof Onboard ? JOINED_EMPLOYEES : EXITED_EMPLOYEES;
    }


    private static Map<String, Map<Month, StuffingMonthlyReport>> collectEmployeesInfoBySamplingEvents(
            Map<String, Employee> dataSource,
            Map<String, Map<Month, List<Event>>> samplingByJoinedAndExitedEvents
    ) {
        return samplingByJoinedAndExitedEvents.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey, // Left the same key type (JOINED or EXITED) in place.
                        eventsByStuffingEntry -> eventsByStuffingEntry.getValue()
                                .entrySet().stream()
                                .collect(Collectors.toMap(
                                        Map.Entry::getKey, // Left the same key MONTH in place.
                                        eventsByMonthsEntry -> new StuffingMonthlyReport(
                                                eventsByMonthsEntry.getValue().size(),
                                                eventsByMonthsEntry.getValue()
                                                        .stream()
                                                        .map(event -> dataSource.get(event.getEmpId()))
                                                        .toList()
                                        ))
                                )
                ));
    }

    private List<Report<String>> generateStuffingReports(
            Map<String, Map<Month, StuffingMonthlyReport>> samplingByJoinedAndExitedEmployeesByMonth
    ) {
        return samplingByJoinedAndExitedEmployeesByMonth.entrySet().stream()
                .map(reportEntry -> new Report<>(
                        reportEntry.getKey(),
                        CURRENT_REPORT_HEADERS + this.format(reportEntry.getValue())
                ))
                .toList();
    }
}