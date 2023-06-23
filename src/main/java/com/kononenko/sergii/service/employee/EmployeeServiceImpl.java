package com.kononenko.sergii.service.employee;

import com.kononenko.sergii.model.Employee;
import com.kononenko.sergii.repository.EmployeesRepository;

import java.util.Arrays;

public class EmployeeServiceImpl implements EmployeeService {

    @Override
    public Employee createAndGet(String empId) {
        return EmployeesRepository.getAllEmployees()
                .computeIfAbsent(empId, Employee::new);
    }

    @Override
    public void fillData(Employee employee, String[] dataPoint) {
        var empDataWithoutEventData =
                Arrays.copyOfRange(dataPoint, 0, dataPoint.length - 4).length;
        var fName = dataPoint[2];
        if (empDataWithoutEventData == 4) {
            employee.setPersonalData(fName, dataPoint[3]);
        } else if (empDataWithoutEventData == 5) {
            employee.setPersonalData(fName, dataPoint[3], dataPoint[4]);
        }
    }
}
