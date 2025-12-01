package com.assignment2.hrmps.service;

import com.assignment2.hrmps.model.Employee;
import com.assignment2.hrmps.model.PayrollRecord;
import com.assignment2.hrmps.repository.PayrollRepository;

import java.time.YearMonth;

public class PayrollService {

    private final PayrollRepository payrollRepository;

    // Some tax rate (can be replaced)
    private final double TAX_RATE = 0.20;

    public PayrollService(PayrollRepository payrollRepository) {
        this.payrollRepository = payrollRepository;
    }
    //Method used to calculate the payroll
    public PayrollRecord calculatePayroll(Employee employee,
                                          YearMonth period,
                                          double hoursWorked,
                                          double overtimeHours,
                                          double bonuses,
                                          double otherDeductions) {
        double basePay;
        //checking if it is hourly or salaried
        switch (employee.getEmploymentType()) {
            case HOURLY -> basePay = employee.getHourlyRate() * hoursWorked;
            case SALARIED -> basePay = employee.getMonthlySalary();
            default -> basePay = 0;
        }
        //Overtime paid 50% more
        double overtimePay = overtimeHours * employee.getHourlyRate() * 1.5;
        double gross = basePay + overtimePay + bonuses;
        double tax = gross * TAX_RATE;
        double net = gross - tax - otherDeductions;
        //Creating payroll record with all the fields
        PayrollRecord record = new PayrollRecord();
        record.setEmployeeId(employee.getId());
        record.setPeriod(period);
        record.setHoursWorked(hoursWorked);
        record.setOvertimeHours(overtimeHours);
        record.setBonuses(bonuses);
        record.setGrossPay(gross);
        record.setTaxAmount(tax);
        record.setOtherDeductions(otherDeductions);
        record.setNetPay(net);
        //Adding it to repo
        payrollRepository.add(record);
        return record;
    }
}