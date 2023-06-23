package com.kononenko.sergii.util;

import com.kononenko.sergii.model.Employee;
import com.kononenko.sergii.model.event.sub.payment.Bonus;
import com.kononenko.sergii.model.event.sub.payment.Reimbursement;
import com.kononenko.sergii.model.event.sub.payment.Salary;
import com.kononenko.sergii.model.event.sub.stuffing.Exit;
import com.kononenko.sergii.model.event.sub.stuffing.Onboard;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static com.kononenko.sergii.constant.DateFormat.EVENT_DATE_FORMAT;

public class SourcePredefinedDataUtil {

    //Run app with args: DataOrb Employee_details_BONUS.csv Employee_details_EXIT.csv Employee_details_ONBOARDING.csv Employee_details_REIMBURSEMENT.csv Employee_details_SALARY.csv

    private static final Map<String, Employee> EMPLOYEES_TEST_DATA = new HashMap<>();

    public static Map<String, Employee> init() {
        var emp101 = "emp101";
        var bill = new Employee(emp101);
        bill.setPersonalData("Bill", "Gates", "Software Engineer");

        var billOnboard = new Onboard(getEventDate("10-10-2021"), "“Bill Gates is going to join DataOrb on 1st November as a SE.”", getEventDate("1-11-2021"));
        var billSalary = new Salary(getEventDate("10-10-2022"), "“Salary of OCT 2020 month”", 4000);
        var billBonus = new Bonus(getEventDate("10-10-2022"), "“Performance bonus of year 2022”", 1000);
        var billReimbursement = new Reimbursement(getEventDate("10-10-2022"), "“Traveling expenses”", 100);
        var billExit = new Exit(getEventDate("10-10-2022"), "“Leaving for further study”", getEventDate("1-12-2022"));

        billOnboard.setEmpId(emp101);
        billSalary.setEmpId(emp101);
        billBonus.setEmpId(emp101);
        billReimbursement.setEmpId(emp101);
        billExit.setEmpId(emp101);

        bill.addEvent(billOnboard);
        bill.addEvent(billSalary);
        bill.addEvent(billBonus);
        bill.addEvent(billReimbursement);
        bill.addEvent(billExit);


        var emp102 = "emp102";
        var steve = new Employee(emp102);
        steve.setPersonalData("Steve Jobs", "Architect");

        var steveOnboard = new Onboard(getEventDate("10-10-2022"), "“Steve Jobs joined DataOrb on 1st October as an Architect.”", getEventDate("1-10-2022"));
        var steveSalary = new Salary(getEventDate("10-10-2022"), "“Oct Salary of Steve.”", 3000);

        steveOnboard.setEmpId(emp102);
        steveSalary.setEmpId(emp102);
        steve.addEvent(steveOnboard);
        steve.addEvent(steveSalary);


        var emp103 = "emp103";
        var martin = new Employee(emp103);
        martin.setPersonalData("Martin", "Fowler", "Software Engineer");
        var martinOnboard = new Onboard(getEventDate("10-10-2022"), "“Martin has joined DataOrb as a SE.”", getEventDate("15-10-2022"));
        martinOnboard.setEmpId(emp103);
        martin.addEvent(martinOnboard);

        EMPLOYEES_TEST_DATA.put("emp101", bill);
        EMPLOYEES_TEST_DATA.put("emp102", steve);
        EMPLOYEES_TEST_DATA.put("emp103", martin);

        return EMPLOYEES_TEST_DATA;
    }

    public static void clear() {
        EMPLOYEES_TEST_DATA.clear();
    }

    private static LocalDate getEventDate(String date) {
        return Converter.stringToLocalDateTime(date, EVENT_DATE_FORMAT);
    }
}