package com.kononenko.sergii.repository;

import com.kononenko.sergii.model.Employee;

import java.util.HashMap;
import java.util.Map;

public class EmployeesRepository {

    // This part can be implemented with SQL DB, for example, to make query for any type of reports in the future needs.
    private static final Map<String, Employee> EMPLOYEES = new HashMap<>();

    public static Map<String, Employee> getAllEmployees() {
        return EMPLOYEES;
    }

    private EmployeesRepository() {
    }
}
