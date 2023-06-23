package com.kononenko.sergii.service.output;

import com.kononenko.sergii.service.report.ReportService;
import com.kononenko.sergii.service.report.impl.*;

import java.util.List;
import java.util.function.Supplier;

public class ReportsHolder {

    public static final List<Supplier<ReportService>> ALL_REPORTS =
            List.of(
                    AllEmployeesReportService::new,
                    StuffingMonthlyReportService::new,
                    MonthlySalaryReportService::new,
                    EmployeeWiseFinancialReportService::new,
                    MonthlyAmountReportService::new,
                    YearlyFinancialReportService::new
            );

    private ReportsHolder() {
    }
}
