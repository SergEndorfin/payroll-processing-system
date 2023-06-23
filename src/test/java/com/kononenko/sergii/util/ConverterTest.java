package com.kononenko.sergii.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class ConverterTest {

    String format = "d-MM-yyyy";

    @Test
    void givenOnly1DigitInADate_whenStringToLocalDateTime_thenReturnLocalDate() {
        String data = "1-12-2023";
        LocalDate localDate = Converter.stringToLocalDateTime(data, format);
        checkResults(1, localDate);
    }

    @Test
    void given2DigitInADate_whenStringToLocalDateTime_thenReturnLocalDate() {
        String data = "31-12-2023";
        LocalDate localDate = Converter.stringToLocalDateTime(data, format);
        checkResults(31, localDate);
    }

    private static void checkResults(int expectedDayOfMonth, LocalDate localDate) {
        assertEquals(expectedDayOfMonth, localDate.getDayOfMonth());
        assertEquals(Month.DECEMBER, localDate.getMonth());
        assertEquals(2023, localDate.getYear());
    }
}