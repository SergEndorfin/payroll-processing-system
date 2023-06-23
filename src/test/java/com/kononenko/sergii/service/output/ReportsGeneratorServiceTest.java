package com.kononenko.sergii.service.output;

import com.kononenko.sergii.model.Employee;
import com.kononenko.sergii.service.report.ReportService;
import com.kononenko.sergii.util.FileSystemCreator;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ReportsGeneratorServiceTest {

    List<Supplier<ReportService>> reportsToBeGeneratedMock = mock(List.class);
    Map<String, Employee> dataSource = new HashMap<>();
    FileSystemCreator fileSystemCreatorMock = mock(FileSystemCreator.class);
    ReportsGeneratorServiceImpl reportsGeneratorService =
            new ReportsGeneratorServiceImpl(reportsToBeGeneratedMock, dataSource, fileSystemCreatorMock);

    @Test
    void generate() {
        var companyName = "companyName";
        var companyFolderPath = "./companyName/";
        var streamMock = mock(Stream.class);

        when(fileSystemCreatorMock.createFolderIfNotExist(companyName)).thenReturn(companyFolderPath);
        when(reportsToBeGeneratedMock.parallelStream()).thenReturn(streamMock);
        when(streamMock.map(any())).thenReturn(Stream.empty());

        reportsGeneratorService.generate(companyName);

        verify(fileSystemCreatorMock).createFolderIfNotExist(companyName);
        verify(reportsToBeGeneratedMock).parallelStream();
        verify(streamMock).map(any());
    }
}