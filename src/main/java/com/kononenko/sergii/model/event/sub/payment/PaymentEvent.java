package com.kononenko.sergii.model.event.sub.payment;

import com.kononenko.sergii.model.event.Event;

import java.time.LocalDate;

public abstract class PaymentEvent extends Event {

    protected final long amountInUsd;

    protected PaymentEvent(LocalDate eventDate, String notes, long amountInUsd) {
        super(eventDate, notes);
        this.amountInUsd = amountInUsd;
    }

    public long getAmountInUsd() {
        return amountInUsd;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName().toUpperCase() +
                ", " + empId +
                ", " + eventDate +
                ", " + amountInUsd;
    }
}