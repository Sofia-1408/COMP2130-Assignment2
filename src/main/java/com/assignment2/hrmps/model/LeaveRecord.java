package com.assignment2.hrmps.model;

import java.io.Serializable;
import java.time.LocalDate;

public class LeaveRecord implements Serializable {

    private String employeeName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String type;   // Vacation, Sick, Personal
    private String status; // Pending, Approved, Rejected

    public LeaveRecord(String employeeName, LocalDate startDate, LocalDate endDate,
                       String type, String status) {
        this.employeeName = employeeName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
        this.status = status;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }
}