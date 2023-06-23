package com.kononenko.sergii.service.report;

import com.kononenko.sergii.model.Employee;
import com.kononenko.sergii.model.event.sub.stuffing.Onboard;
import com.kononenko.sergii.model.report.StuffingMonthlyReport;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static com.kononenko.sergii.util.Converter.stringToLocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StuffingMonthlyReportFormatterServiceTest implements StuffingMonthlyReportFormatterService {

    @Test
    void formatTest() {
        var key = "OCTOBER";
        var separator = ", ";
        var totalEmpInAReport = 2;

        var emp101 = "emp101";
        var bill = new Employee(emp101);
        var billDesignation = "Software Engineer";
        bill.setPersonalData("Bill", "Gates", billDesignation);
        var billEvent = new Onboard(getEventDate(), "“Onboard”", getEventDate());
        billEvent.setEmpId(emp101);
        bill.addEvent(billEvent);

        var emp102 = "emp102";
        var steve = new Employee(emp102);
        var steveDesignation = "Architect";
        steve.setPersonalData("Steve Jobs", steveDesignation);
        var steveEvent = new Onboard(getEventDate(), "“Onboard”", getEventDate());
        steveEvent.setEmpId(emp102);
        steve.addEvent(steveEvent);

        StuffingMonthlyReport stuffingMonthlyReport = new StuffingMonthlyReport(totalEmpInAReport, List.of(bill, steve));

        var actualResult = this.format(Map.of(key, stuffingMonthlyReport));
        var expected = key + separator + totalEmpInAReport + separator + emp101 + separator + billDesignation + separator +
                bill.getEmpFName() + separator + bill.getEmpLName() +
                "\n" +
                key + separator + totalEmpInAReport + separator + emp102 + separator + steveDesignation + separator +
                steve.getEmpFName() + separator + "EMPTY";

        assertEquals(expected, actualResult);
    }

    private static LocalDate getEventDate() {
        return stringToLocalDateTime("10-10-2022", "d-MM-yyyy");
    }
}