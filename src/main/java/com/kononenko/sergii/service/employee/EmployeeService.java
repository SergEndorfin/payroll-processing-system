package com.kononenko.sergii.service.employee;

import com.kononenko.sergii.model.Employee;

public interface EmployeeService {

    Employee createAndGet(String empId);

    void fillData(Employee employee, String[] dataPoint);
}
