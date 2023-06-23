package com.kononenko.sergii.service.event;

import com.kononenko.sergii.model.event.Event;
import com.kononenko.sergii.model.event.sub.payment.PaymentEvent;
import com.kononenko.sergii.model.event.sub.stuffing.StaffingEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Month;

import static com.kononenko.sergii.model.event.EventType.*;
import static org.junit.jupiter.api.Assertions.*;

class EventBuilderFactoryTest {

    static String eventDateValue = "1-11-2022";
    static String eventNumberValue = "1000";
    static String eventDate = "1-10-2022";
    static String eventNotes = "“Architect.”";
    String[] onboardDataPoint = {eventDateValue, eventDate, eventNotes};

    @ParameterizedTest
    @ValueSource(strings = {ONBOARD, EXIT})
    void createAndBuildStuffingEvent(String eventType) {
        var event = (StaffingEvent) EventBuilderFactory.createAndBuild(eventType, onboardDataPoint).apply(eventDateValue);
        checkCommonEventData(eventType, event);
        assertEquals(1, event.getStaffingEventValue().getDayOfMonth());
        assertEquals(Month.NOVEMBER, event.getStaffingEventValue().getMonth());
        assertEquals(2022, event.getStaffingEventValue().getYear());
    }

    @ParameterizedTest
    @ValueSource(strings = {SALARY, BONUS, REIMBURSEMENT})
    void createAndBuildPaymentEvent(String eventType) {
        String[] onboardDataPoint = {eventNumberValue, eventDate, eventNotes};
        var event = (PaymentEvent) EventBuilderFactory.createAndBuild(eventType, onboardDataPoint)
                .apply(eventNumberValue);
        checkCommonEventData(eventType, event);
        assertEquals(Long.parseLong(eventNumberValue), event.getAmountInUsd());
    }

    @Test
    void whenExceptionThrown_thenAssertionSucceeds() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () ->
                EventBuilderFactory.createAndBuild("DUMMY_TYPE", onboardDataPoint).apply(eventDateValue)
        );
        String expectedMessage = "Unexpected event type value";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        assertEquals(expectedMessage, actualMessage);
    }

    private void checkCommonEventData(String eventType, Event event) {
        assertEquals(eventType, event.getClass().getSimpleName().toUpperCase());
        assertEquals(Month.OCTOBER, event.getEventDate().getMonth());
        assertEquals(1, event.getEventDate().getDayOfMonth());
        assertEquals(2022, event.getEventDate().getYear());
        assertEquals(eventNotes, event.getNotes());
    }
}