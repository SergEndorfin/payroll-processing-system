package com.kononenko.sergii.service.event.builder;

import com.kononenko.sergii.model.event.Event;
import com.kononenko.sergii.model.event.sub.stuffing.Exit;
import com.kononenko.sergii.model.event.sub.stuffing.Onboard;

import java.time.LocalDate;
import java.util.function.Function;

import static com.kononenko.sergii.model.event.EventType.ONBOARD;

public class StaffingEventBuilder implements EventBuilder<LocalDate> {

    @Override
    public Function<LocalDate, Event> build(String eventType, LocalDate eventDate, String notes) {
        if (eventType.equals(ONBOARD)) {
            return staffingDate -> new Onboard(eventDate, notes, staffingDate);
        }
        return staffingDate -> new Exit(eventDate, notes, staffingDate);
    }
}
