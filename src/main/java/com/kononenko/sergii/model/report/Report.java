package com.kononenko.sergii.model.report;

public class Report<T> {

    private String name;
    private final T value;

    public Report(String name, T value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getValue() {
        return value;
    }
}