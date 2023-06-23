package com.kononenko.sergii.model.event.sub.stuffing;

import java.time.LocalDate;

public class Onboard extends StaffingEvent {

    public Onboard(LocalDate eventDate, String notes, LocalDate dateOfJoining) {
        super(eventDate, notes, dateOfJoining);
    }
}
