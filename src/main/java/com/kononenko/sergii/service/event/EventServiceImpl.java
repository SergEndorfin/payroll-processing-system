package com.kononenko.sergii.service.event;

import com.kononenko.sergii.model.event.Event;

import java.util.Arrays;

public class EventServiceImpl implements EventService {

    public Event createAndGet(String[] dataPoint, String empId) {
        var eventType = dataPoint[dataPoint.length - 4];
        var eventRowData = Arrays.copyOfRange(dataPoint, dataPoint.length - 3, dataPoint.length);
        var eventValue = eventRowData[0];
        var event = EventBuilderFactory.createAndBuild(eventType, eventRowData).apply(eventValue);
        event.setEmpId(empId);
        return event;
    }
}