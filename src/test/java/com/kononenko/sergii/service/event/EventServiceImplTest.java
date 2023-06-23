package com.kononenko.sergii.service.event;

import com.kononenko.sergii.model.event.Event;
import com.kononenko.sergii.model.event.sub.stuffing.Onboard;
import com.kononenko.sergii.model.event.sub.stuffing.StaffingEvent;
import com.kononenko.sergii.util.Converter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class EventServiceImplTest {

    EventBuilderFactory eventBuilderFactoryMock = mock(EventBuilderFactory.class);

    static String empId = "empId";
    static String eventType = "ONBOARD";
    static String eventValue = "1-11-2022";
    static String eventDate = "1-10-2022";
    static String eventNotes = "“Architect.”";
    EventService eventService = new EventServiceImpl();

    @ParameterizedTest
    @MethodSource("provideSplittedDataPointRecords")
    void createAndGet(String[] dataPoint) {
        var event = eventService.createAndGet(dataPoint, empId);
        assertEquals(empId, event.getEmpId());
        assertEquals(eventType, event.getClass().getSimpleName().toUpperCase());
        assertEquals(2022, ((StaffingEvent)event).getStaffingEventValue().getYear());
        assertEquals(2022, event.getEventDate().getYear());
        assertEquals(eventNotes, event.getNotes());
    }

    private static Stream<Arguments> provideSplittedDataPointRecords() {
        return Stream.of(
                Arguments.of((Object) new String[]{"1", empId, "firstName", "designation", eventType, eventValue, eventDate, eventNotes}),
                Arguments.of((Object) new String[]{"2", empId, "firstName", "lastName", "designation", eventType, eventValue, eventDate, eventNotes}),
                Arguments.of((Object) new String[]{"3", empId, eventType, eventValue, eventDate, eventNotes})
        );
    }
}