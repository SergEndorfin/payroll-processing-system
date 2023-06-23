package com.kononenko.sergii.service.event.builder;

import com.kononenko.sergii.model.event.Event;

import java.time.LocalDate;
import java.util.function.Function;

public interface EventBuilder<T> {

    Function<T, Event> build(String eventType, LocalDate eventDate, String notes);
}
