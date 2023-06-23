package com.kononenko.sergii.service.report;

import org.junit.jupiter.api.Test;

import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReportFormatterServiceTest implements ReportFormatterService {

    @Test
    void formatTest() {
        String key1 = "key1";
        String value1 = "value1";
        String key2 = "key2";
        String value2 = "value2";

        TreeMap<Object, Object> testData = new TreeMap<>();
        testData.put(key1, value1);
        testData.put(key2, value2);

        String result = this.format(testData);
        String expected = key1 + ", " + value1 + "\n" + key2 + ", " + value2;
        assertEquals(expected, result);
    }
}