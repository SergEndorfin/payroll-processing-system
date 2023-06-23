package com.kononenko.sergii.model.event;

import java.time.LocalDate;

public abstract class Event {

    protected String empId;
    protected LocalDate eventDate; //DD-MM-YYYY
    protected String notes;

    protected Event(LocalDate eventDate, String notes) {
        this.eventDate = eventDate;
        this.notes = notes;
    }

    protected Event() {
    }

    public String getEmpId() {
        return empId;
    }

    public String getNotes() {
        return notes;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }
}
