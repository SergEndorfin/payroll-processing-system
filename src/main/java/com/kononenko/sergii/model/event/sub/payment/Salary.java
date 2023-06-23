package com.kononenko.sergii.model.event.sub.payment;

import java.time.LocalDate;

public class Salary extends PaymentEvent {

    public Salary(LocalDate eventDate, String notes, long amountInUsd) {
        super(eventDate, notes, amountInUsd);
    }
}
