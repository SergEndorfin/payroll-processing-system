package com.kononenko.sergii.service.output;

import com.kononenko.sergii.model.report.Report;
import com.kononenko.sergii.util.FileSystemCreator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class WriteDataServiceTest {

    FileSystemCreator fileSystemCreator = new FileSystemCreator();
    WriteDataService writeDataService = new WriteDataService();

    String companyNameFolder = "company_name";
    String fileExtension = ".csv";
    String absolutePathToReportsDir;
    String reportOuter = "reportOuter";

    @BeforeEach
    void init() {
        absolutePathToReportsDir = fileSystemCreator.createFolderIfNotExist(companyNameFolder);
    }

    @AfterEach
    void clear() {
        String absolutePathToFolderWithoutLineSeparatorAtTheEnd =
                absolutePathToReportsDir.replaceAll(File.separator + "$", "");
        new File(absolutePathToFolderWithoutLineSeparatorAtTheEnd).delete();
    }

    @Test
    void writeWhenSimpleReport() {
        Report<String> report = new Report<>(reportOuter, "value");
        writeDataService.write(absolutePathToReportsDir, report);

        String expectedAbsolutePathToFile = absolutePathToReportsDir + reportOuter + fileExtension;

        File file = new File(expectedAbsolutePathToFile);
        assertTrue(file.exists());
        assertTrue(file.delete());
    }

    @Test
    void writeWhenReportOfReportsIncluded() {
        String reportName1 = "inner_1";
        String reportName2 = "inner_2";
        Report<String> inner1 = new Report<>(reportName1, "value_1");
        Report<String> inner2 = new Report<>(reportName2, "value_2");
        Report<List<Report<String>>> outer = new Report<>(reportOuter, List.of(inner1, inner2));

        writeDataService.write(absolutePathToReportsDir, outer);

        String expectedAbsolutePathToFile1 = getExpectedFileNameForComplexReport(reportName1);
        String expectedAbsolutePathToFile2 = getExpectedFileNameForComplexReport(reportName2);
        File file1 = new File(expectedAbsolutePathToFile1);
        File file2 = new File(expectedAbsolutePathToFile2);

        assertTrue(file1.exists());
        assertTrue(file2.exists());
        assertTrue(file1.delete());
        assertTrue(file2.delete());
    }

    private String getExpectedFileNameForComplexReport(String reportInnerName) {
        return absolutePathToReportsDir + reportOuter + "_" + reportInnerName + fileExtension;
    }
}