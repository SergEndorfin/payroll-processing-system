package com.kononenko.sergii.service.input;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadDataService {

    private static final int MINIMUM_ITEMS_IN_ROW = 6;
    private static final String LINE_SEPARATOR = ", ?";
    public static final String ERROR_MESSAGE = "Unsupported input data format. Check this row in your input file: ";

    private final DataAccumulatorService dataAccumulatorService;

    public ReadDataService(DataAccumulatorService dataAccumulatorService) {
        this.dataAccumulatorService = dataAccumulatorService;
    }

    public void read(String[] inputFiles) {
        for (String file : inputFiles) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] dataPoint = checkAndGet(line);
                    if (dataPoint == null) continue;
                    handleDataPointAndSaveToStore(line, dataPoint);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String[] checkAndGet(String line) {
        var dataPoint = line.split(LINE_SEPARATOR);
        if (dataPoint.length < MINIMUM_ITEMS_IN_ROW) {
            logErrorIfException(line);
            return null;
        }
        return dataPoint;
    }

    private void handleDataPointAndSaveToStore(String line, String[] dataPoint) {
        try {
            dataAccumulatorService.save(dataPoint);
        } catch (Exception e) {
            logErrorIfException(line, e.getMessage());
            // e.printStackTrace(); if needed to track the reason we may do it with log level feature.
        }
    }

    private static void logErrorIfException(String line) {
        System.err.println(ERROR_MESSAGE + line);
    }

    private static void logErrorIfException(String line, String msg) {
        System.err.println(ERROR_MESSAGE + line);
        System.err.println(msg);
    }
}