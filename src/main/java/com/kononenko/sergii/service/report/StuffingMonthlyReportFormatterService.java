package com.kononenko.sergii.service.report;

import com.kononenko.sergii.model.report.StuffingMonthlyReport;

import java.util.Map;
import java.util.stream.Collectors;

public interface StuffingMonthlyReportFormatterService {

    default <K, V> String format(Map<K, V> report) {
        return report.entrySet().stream()
                .map(entry -> {
                            K key = entry.getKey();
                            StuffingMonthlyReport stuffingMonthlyReport = (StuffingMonthlyReport) entry.getValue();
                            return stuffingMonthlyReport
                                    .employees()
                                    .stream()
                                    .map(emp -> "" + key + ", " + stuffingMonthlyReport.count() + ", " +
                                            emp.getEmpID() + ", " + emp.getDesignation() + ", " + emp.getEmpFName() +
                                            ", " + (emp.getEmpLName() != null ? emp.getEmpLName() : "EMPTY")
                                    )
                                    .collect(Collectors.joining("\n"));
                        }
                )
                .collect(Collectors.joining("\n"));
    }
}
