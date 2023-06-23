package com.kononenko.sergii.service.output;

import com.kononenko.sergii.model.report.Report;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriteDataService {

    private static final String FILE_EXTENSION = ".csv";

    public void write(String companyFolderName, Report report) {
        if (!(report.getValue() instanceof List)) {
            this.writeToFile(companyFolderName, report);
        } else {
            String namePrefix = report.getName();
            ((List<Report<String>>) report.getValue()).forEach(rep -> {
                String reportFileName = namePrefix + "_" + rep.getName();
                rep.setName(reportFileName);
                this.writeToFile(companyFolderName, rep);
            });
        }
    }

    private void writeToFile(String companyFolderName, Report<String> report) {
        File file = new File(companyFolderName + report.getName() + FILE_EXTENSION);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(report.getValue());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}