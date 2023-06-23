package com.kononenko.sergii.model.event.sub.payment;

import java.time.LocalDate;

public class Reimbursement extends PaymentEvent {

    public Reimbursement(LocalDate eventDate, String notes, long amountInUsd) {
        super(eventDate, notes, amountInUsd);
    }
}
