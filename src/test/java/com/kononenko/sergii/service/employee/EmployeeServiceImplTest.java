package com.kononenko.sergii.service.employee;

import com.kononenko.sergii.model.Employee;
import com.kononenko.sergii.repository.EmployeesRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class EmployeeServiceImplTest {

    EmployeeService employeeService = new EmployeeServiceImpl();
    String empId = "empId";


    @BeforeEach
    void init() {
        EmployeesRepository.getAllEmployees().clear();
    }

    @AfterEach
    void clear() {
        EmployeesRepository.getAllEmployees().clear();
    }

    @Test
    void createAndGet() {
        Employee employee = employeeService.createAndGet(empId);
        assertEquals(1, EmployeesRepository.getAllEmployees().size());
        assertEquals(empId, employee.getEmpID());
    }

    @Test
    void fillDataIfEmployeeDoesNotHaveLastName() {
        var firstName = "Steve Jobs";
        var designation = "Architect";
        var employee = new Employee(empId);
        String[] dataPoint = {"2", empId, firstName, designation, "ONBOARD", "1-10-2022", "10-10-2022", "“Architect.”"};
        employeeService.fillData(employee, dataPoint);
        checkResults(firstName, designation, employee);
        assertNull(employee.getEmpLName());
    }

    @Test
    void fillDataIfEmployeeHaveLastName() {
        var firstName = "Bill";
        var lastName = "Gates";
        var designation = "Architect";
        var employee = new Employee(empId);
        String[] dataPoint = {"1", empId, firstName, lastName, designation, "ONBOARD", "1-10-2022", "10-10-2022", "“Architect.”"};
        employeeService.fillData(employee, dataPoint);
        checkResults(firstName, designation, employee);
        assertEquals(lastName, employee.getEmpLName());
    }

    private void checkResults(String firstName, String designation, Employee employee) {
        assertEquals(empId, employee.getEmpID());
        assertEquals(firstName, employee.getEmpFName());
        assertEquals(designation, employee.getDesignation());
    }
}