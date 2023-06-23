package com.kononenko.sergii.util;

public class InputDataGenerator {
    // make some data for large reports quickly
    public static void generate() {
        for (int i = 1; i <= 5000; i++) {
            int month = i % 12 + 1;
            int year = 2;
            System.out.println(i + ", " + "emp" + i + ", Name_" + i + ", lastN_" + i + ", Software Engineer, ONBOARD, 1-" + month + "-202" + year + ", 29-" + month + "-202" + year + ", “Emp is going to join DataOrb on 1st " + month + " as a SE.”");
            System.out.println(i + ", emp" + i + ", EXIT, 1-" + month + "-2022, 10-" + month + "-2022, “Leaving for further study”");
            System.out.println(i + ", emp" + i + ", SALARY, " + month + "000, 10-" + month + "-2020, “Salary of " + month + " month”");
            System.out.println(i + ", emp" + i + ", BONUS, " + month + "00, 10-" + month + "-2020, “Performance bonus of year 2020”");
            System.out.println(i + ", emp" + i + ", REIMBURSEMENT, 100, 10-" + month + "-2020, “Traveling expenses”");
        }
    }
}
