package com.kononenko.sergii.model.event.sub.stuffing;

import com.kononenko.sergii.model.event.Event;

import java.time.LocalDate;

public abstract class StaffingEvent extends Event {

    protected final LocalDate staffingEventValue;

    protected StaffingEvent(LocalDate eventDate, String notes, LocalDate staffingEventValue) {
        super(eventDate, notes);
        this.staffingEventValue = staffingEventValue;
    }

    public LocalDate getStaffingEventValue() {
        return staffingEventValue;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName().toUpperCase() +
                ", " + empId +
                ", " + eventDate +
                ", " + staffingEventValue;
    }
}
