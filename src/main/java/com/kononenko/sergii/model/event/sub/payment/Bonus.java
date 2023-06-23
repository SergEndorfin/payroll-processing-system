package com.kononenko.sergii.model.event.sub.payment;

import java.time.LocalDate;

public class Bonus extends PaymentEvent {

    public Bonus(LocalDate eventDate, String notes, long amountInUsd) {
        super(eventDate, notes, amountInUsd);
    }

}
