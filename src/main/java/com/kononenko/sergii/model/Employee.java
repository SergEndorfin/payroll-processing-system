package com.kononenko.sergii.model;

import com.kononenko.sergii.model.event.Event;

import java.util.ArrayList;
import java.util.List;

public class Employee {

    private final String empID;
    private String empFName;
    private String empLName;
    private String designation;

    public List<Event> getEvents() {
        return events;
    }

    private List<Event> events;

    public Employee(String empID) {
        this.empID = empID;
    }

    public void addEvent(Event event) {
        if (this.events == null)
            this.events = new ArrayList<>();
        this.events.add(event);
    }

    public String getEmpID() {
        return empID;
    }

    public String getEmpFName() {
        return empFName;
    }

    public String getEmpLName() {
        return empLName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setPersonalData(String fName, String designation) {
        this.empFName = fName;
        this.designation = designation;
    }

    public void setPersonalData(String fName, String lName, String designation) {
        this.empLName = lName;
        setPersonalData(fName, designation);
    }
}
