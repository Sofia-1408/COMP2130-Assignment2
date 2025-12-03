package com.assignment2.hrmps.model;

import java.io.Serializable;
import java.time.LocalDate;

public class AttendanceRecord implements Serializable {

    private String employeeName;
    private LocalDate date;
    private String status; // Present, Absent, Sick

    public AttendanceRecord(String employeeName, LocalDate date, String status) {
        this.employeeName = employeeName;
        this.date = date;
        this.status = status;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }
}