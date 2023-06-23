package com.kononenko.sergii.service.report;

import java.util.Map;
import java.util.stream.Collectors;

public interface ReportFormatterService {

    default <K, V> String format(Map<K, V> report) {
        return report.entrySet().stream()
                .map(entry -> "" + entry.getKey() + ", " + entry.getValue())
                .collect(Collectors.joining("\n"));
    }
}
