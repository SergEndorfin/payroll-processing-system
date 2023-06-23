package com.kononenko.sergii;

import com.kononenko.sergii.repository.EmployeesRepository;
import com.kononenko.sergii.service.employee.EmployeeServiceImpl;
import com.kononenko.sergii.service.event.EventServiceImpl;
import com.kononenko.sergii.service.input.DataAccumulatorServiceImpl;
import com.kononenko.sergii.service.input.ReadDataService;
import com.kononenko.sergii.service.output.ReportsGeneratorServiceImpl;
import com.kononenko.sergii.service.output.ReportsHolder;
import com.kononenko.sergii.util.FileSystemCreator;

import java.util.Arrays;

public class PayrollProcessingSystem {


    public static void main(String[] args) {
        String companyName = args[0];
        String[] inputFiles = Arrays.copyOfRange(args, 1, args.length);

        new ReadDataService(
                new DataAccumulatorServiceImpl(
                        new EventServiceImpl(),
                        new EmployeeServiceImpl()
                )
        ).read(inputFiles);

        new ReportsGeneratorServiceImpl(
                ReportsHolder.ALL_REPORTS,
                EmployeesRepository.getAllEmployees(),
                new FileSystemCreator()
        ).generate(companyName);
    }
}