package com.kononenko.sergii.service.event.builder;

import com.kononenko.sergii.model.event.sub.payment.Bonus;
import com.kononenko.sergii.model.event.Event;
import com.kononenko.sergii.model.event.sub.payment.Reimbursement;
import com.kononenko.sergii.model.event.sub.payment.Salary;

import java.time.LocalDate;
import java.util.function.Function;

import static com.kononenko.sergii.model.event.EventType.*;

public class PaymentEventBuilder implements EventBuilder<Long> {

    public Function<Long, Event> build(String eventType, LocalDate eventDate, String notes) {
        return switch (eventType) {
            case SALARY -> amount -> new Salary(eventDate, notes, amount);
            case BONUS -> amount -> new Bonus(eventDate, notes, amount);
            default -> amount -> new Reimbursement(eventDate, notes, amount);
        };
    }
}
