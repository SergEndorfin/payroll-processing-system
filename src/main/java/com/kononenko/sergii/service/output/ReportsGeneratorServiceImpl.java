package com.kononenko.sergii.service.output;

import com.kononenko.sergii.model.Employee;
import com.kononenko.sergii.service.report.ReportService;
import com.kononenko.sergii.util.FileSystemCreator;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ReportsGeneratorServiceImpl implements ReportsGeneratorService {

    private final List<Supplier<ReportService>> reportsToBeGenerated;
    private final Map<String, Employee> dataSource;
    private final FileSystemCreator fileSystemCreator;

    public ReportsGeneratorServiceImpl(List<Supplier<ReportService>> reportsToBeGenerated,
                                       Map<String, Employee> dataSource,
                                       FileSystemCreator fileSystemCreator) {
        this.reportsToBeGenerated = reportsToBeGenerated;
        this.dataSource = dataSource;
        this.fileSystemCreator = fileSystemCreator;
    }

    @Override
    public void generate(String companyName) {
        var companyFolderPath = fileSystemCreator.createFolderIfNotExist(companyName);
        reportsToBeGenerated
                .parallelStream()
                .map(Supplier::get)
                .map(reportService -> reportService.getReport(dataSource))
                .forEach(report -> new WriteDataService().write(companyFolderPath, report));
    }
}