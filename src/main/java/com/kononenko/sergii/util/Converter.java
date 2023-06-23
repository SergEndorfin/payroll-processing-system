package com.kononenko.sergii.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Converter {

    public static LocalDate stringToLocalDateTime(String data, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDate.parse(data, formatter);
    }

    private Converter() {
    }
}
