package com.kononenko.sergii.service.event;

import com.kononenko.sergii.model.event.Event;

public interface EventService {
    Event createAndGet(String[] dataPoint, String empId);
}
