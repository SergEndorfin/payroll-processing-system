package com.kononenko.sergii.model.event.sub.stuffing;

import java.time.LocalDate;

public class Exit extends StaffingEvent {

    public Exit(LocalDate eventDate, String notes, LocalDate dateOfJoining) {
        super(eventDate, notes, dateOfJoining);
    }
}
