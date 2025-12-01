package com.assignment2.hrmps.model;

import java.io.Serializable;
import java.time.YearMonth;

public class PayrollRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;            // e.g. "PR-2025-11-EMP001"
    private String employeeId;
    private YearMonth period;     // e.g. 2025-11
    private double hoursWorked;
    private double overtimeHours;
    private double bonuses;
    private double grossPay;
    private double taxAmount;
    private double otherDeductions;
    private double netPay;
    //Auto generated constructors by intellij
    public PayrollRecord() {}
    public PayrollRecord(double netPay, double otherDeductions, double taxAmount, double grossPay, double bonuses, double overtimeHours, double hoursWorked, YearMonth period, String employeeId, String id) {
        this.netPay = netPay;
        this.otherDeductions = otherDeductions;
        this.taxAmount = taxAmount;
        this.grossPay = grossPay;
        this.bonuses = bonuses;
        this.overtimeHours = overtimeHours;
        this.hoursWorked = hoursWorked;
        this.period = period;
        this.employeeId = employeeId;
        this.id = id;
    }
    //Auto generated getters and setters by intellij
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public YearMonth getPeriod() {
        return period;
    }

    public void setPeriod(YearMonth period) {
        this.period = period;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public double getOvertimeHours() {
        return overtimeHours;
    }

    public void setOvertimeHours(double overtimeHours) {
        this.overtimeHours = overtimeHours;
    }

    public double getBonuses() {
        return bonuses;
    }

    public void setBonuses(double bonuses) {
        this.bonuses = bonuses;
    }

    public double getGrossPay() {
        return grossPay;
    }

    public void setGrossPay(double grossPay) {
        this.grossPay = grossPay;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public double getOtherDeductions() {
        return otherDeductions;
    }

    public void setOtherDeductions(double otherDeductions) {
        this.otherDeductions = otherDeductions;
    }

    public double getNetPay() {
        return netPay;
    }

    public void setNetPay(double netPay) {
        this.netPay = netPay;
    }
}
