package com.kononenko.sergii.service.input;

import com.kononenko.sergii.model.Employee;
import com.kononenko.sergii.model.event.sub.payment.Salary;
import com.kononenko.sergii.service.employee.EmployeeService;
import com.kononenko.sergii.service.event.EventService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DataAccumulatorServiceImplTest {

    EventService eventServiceMock = mock(EventService.class);
    EmployeeService employeeServiceMock = mock(EmployeeService.class);
    DataAccumulatorService dataAccumulatorService =
            new DataAccumulatorServiceImpl(eventServiceMock, employeeServiceMock);

    @Test
    void save() {
        var empId = "empId";
        var employee = new Employee(empId);
        var event = new Salary(LocalDate.now(), "note", 8888);
        String[] dataPoint = {"1", empId, "firstName", "designation", "eventType", "eventValue", "1-12-2023", "eventNotes"};

        when(employeeServiceMock.createAndGet(empId)).thenReturn(employee);
        when(eventServiceMock.createAndGet(dataPoint, empId)).thenReturn(event);
        doNothing().when(employeeServiceMock).fillData(employee, dataPoint);

        dataAccumulatorService.save(dataPoint);

        verify(employeeServiceMock).createAndGet(empId);
        verify(eventServiceMock).createAndGet(dataPoint, empId);
        verify(employeeServiceMock).fillData(employee, dataPoint);

        assertEquals(1, employee.getEvents().size());
    }
}