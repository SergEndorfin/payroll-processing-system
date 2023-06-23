package com.kononenko.sergii.service.input;

import com.kononenko.sergii.service.employee.EmployeeService;
import com.kononenko.sergii.service.employee.EmployeeServiceImpl;
import com.kononenko.sergii.service.event.EventService;
import com.kononenko.sergii.service.event.EventServiceImpl;

public class DataAccumulatorServiceImpl implements DataAccumulatorService {

    private final EventService eventService;
    private final EmployeeService employeeService;

    public DataAccumulatorServiceImpl(EventService eventService, EmployeeService employeeService) {
        this.eventService = eventService;
        this.employeeService = employeeService;
    }

    @Override
    public void save(String[] dataPoint) {
        var empId = dataPoint[1];
        var event = eventService.createAndGet(dataPoint, empId);
        var employee = employeeService.createAndGet(empId);
        employee.addEvent(event);
        employeeService.fillData(employee, dataPoint);
    }
}