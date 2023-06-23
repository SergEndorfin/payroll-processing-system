package com.kononenko.sergii.service.event;

import com.kononenko.sergii.model.event.Event;
import com.kononenko.sergii.service.event.builder.PaymentEventBuilder;
import com.kononenko.sergii.service.event.builder.StaffingEventBuilder;
import com.kononenko.sergii.util.Converter;

import java.util.function.Function;

import static com.kononenko.sergii.constant.DateFormat.EVENT_DATE_FORMAT;
import static com.kononenko.sergii.constant.DateFormat.STUFFING_DATE_FORMAT;
import static com.kononenko.sergii.model.event.EventType.*;

public class EventBuilderFactory {

    public static Function<String, Event> createAndBuild(String eventType, String[] eventRowData) {

        var eventDate = Converter.stringToLocalDateTime(eventRowData[1], EVENT_DATE_FORMAT);
        var notes = eventRowData[2];

        return switch (eventType) {
            case SALARY, BONUS, REIMBURSEMENT -> eventValue -> new PaymentEventBuilder()
                    .build(eventType, eventDate, notes)
                    .apply(Long.parseLong(eventValue));
            case EXIT, ONBOARD -> eventValue -> new StaffingEventBuilder()
                    .build(eventType, eventDate, notes)
                    .apply(Converter.stringToLocalDateTime(eventValue, STUFFING_DATE_FORMAT));
            default -> throw new IllegalStateException("Unexpected event type value");
        };
    }

    private EventBuilderFactory() {
    }
}